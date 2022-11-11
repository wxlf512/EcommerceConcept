package dev.wxlf.feature.details.presentation

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dagger.android.support.AndroidSupportInjection
import dev.wxlf.feature.details.R
import dev.wxlf.feature.details.presentation.viewmodel.DetailsViewModelFactory
import dev.wxlf.feature.details.presentation.models.DetailsDisplayableModel
import dev.wxlf.feature.details.presentation.models.DetailsEvent
import dev.wxlf.feature.details.presentation.models.DetailsViewState
import dev.wxlf.feature.details.presentation.viewmodel.DetailsViewModel
import dev.wxlf.feature.details.presentation.adapters.ViewPagerAdapter
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.roundToInt

class DetailsFragment : Fragment(R.layout.fragment_details) {

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory
    private lateinit var viewModel: DetailsViewModel

    lateinit var details: DetailsDisplayableModel

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.white)
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]
        viewModel.obtainEvent(DetailsEvent.ScreenShown)
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is DetailsViewState.LoadedState -> {
                    details = viewState.data

                    val imgList: MutableList<String> = (List(2) {viewState.data.imagesUrls}.flatten()).toMutableList()
                    val firstElement = viewState.data.imagesUrls[0]
                    val lastElement = viewState.data.imagesUrls[viewState.data.imagesUrls.lastIndex]
                    imgList.add(0, lastElement)
                    imgList.add(firstElement)
                    val viewPager = view.findViewById<ViewPager2>(R.id.imagesViewPager)
                    viewPager.apply {
                        clipChildren = false
                        clipToPadding = false
                        offscreenPageLimit = 3
                        (getChildAt(0) as RecyclerView).overScrollMode =
                            RecyclerView.OVER_SCROLL_NEVER
                    }

                    viewPager.adapter = ViewPagerAdapter(imgList)
                    viewPager.setCurrentItem(1, false)
                    val compositePageTransformer = CompositePageTransformer()
                    compositePageTransformer.addTransformer(MarginPageTransformer((24 * resources.displayMetrics.density).roundToInt()))
                    compositePageTransformer.addTransformer { page, position ->
                        val r = 1 - abs(position)
                        page.scaleY = (0.80f + r * 0.20f)
                    }
                    viewPager.setPageTransformer(compositePageTransformer)
                    val recyclerView = viewPager.getChildAt(0) as RecyclerView
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val itemCount = viewPager.adapter?.itemCount ?: 0

                    recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                        override fun onScrolled(
                            recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            val firstItemVisible
                                    = layoutManager.findFirstVisibleItemPosition()
                            val lastItemVisible
                                    = layoutManager.findLastVisibleItemPosition()
                            if (firstItemVisible == (itemCount - 1) && dx > 0) {
                                recyclerView.scrollToPosition(1)
                            } else if (lastItemVisible == 0 && dx < 0) {
                                recyclerView.scrollToPosition(itemCount - 2)
                            }
                        }
                    })

                    updateDetails()
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }
                is DetailsViewState.ErrorState -> {
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    val errorView = view.findViewById<TextView>(R.id.errorTextView)
                    errorView.visibility = View.VISIBLE
                    errorView.text = "Error: ${viewState.msg}"
                }
                DetailsViewState.LoadingState -> {
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }
            }
        }

        view.findViewById<ImageButton>(R.id.favoriteButton).setOnClickListener {
            details.isFavorite = !details.isFavorite
            updateDetails()
        }


        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        with(tabLayout.getTabAt(tabLayout.selectedTabPosition)) {
            val customView = this?.customView
            if (customView == null)
                this?.setCustomView(R.layout.custom_tab_layout)
            val label = this?.customView?.findViewById<TextView>(R.id.customTabText)
            label?.text = this?.text
            label?.setTextColor(tabLayout.tabTextColors)
            label?.typeface = Typeface.DEFAULT_BOLD
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                if (customView == null)
                    tab?.setCustomView(R.layout.custom_tab_layout)
                val label = tab?.customView?.findViewById<TextView>(R.id.customTabText)
                label?.text = tab?.text
                label?.setTextColor(tabLayout.tabTextColors)
                label?.typeface = Typeface.DEFAULT_BOLD
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                if (customView == null)
                    tab?.setCustomView(R.layout.custom_tab_layout)
                val label = tab?.customView?.findViewById<TextView>(R.id.customTabText)
                label?.text = tab?.text
                label?.setTextColor(tabLayout.tabTextColors)
                label?.typeface = Typeface.DEFAULT
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
        view.findViewById<ImageButton>(R.id.cartButton).setOnClickListener {
            findNavController().navigate(Uri.parse("ecommerceapp://cart"))
        }
        view.findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n", "CutPasteId")
    private fun updateDetails() {
        view?.findViewById<TextView>(R.id.detailsTitle)?.text = details.title
        view?.findViewById<RatingBar>(R.id.detailsRating)?.rating = details.rating.toFloat()
        view?.findViewById<TextView>(R.id.cpu)?.text = details.cpu
        view?.findViewById<TextView>(R.id.camera)?.text = details.camera
        view?.findViewById<TextView>(R.id.ram)?.text = details.ram
        view?.findViewById<TextView>(R.id.memory)?.text = details.memory
        view?.findViewById<TextView>(R.id.price)?.text = details.price

        view?.findViewById<ImageView>(R.id.firstColor)?.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(details.colors[0]))
        view?.findViewById<ImageView>(R.id.secondColor)?.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(details.colors[1]))

        view?.findViewById<TextView>(R.id.firstCapacity)?.text = "${details.capacity[0]} GB"
        view?.findViewById<TextView>(R.id.secondCapacity)?.text = "${details.capacity[1]} GB"

        view?.findViewById<ImageView>(R.id.firstColor)?.setOnClickListener {
            view?.findViewById<ImageView>(R.id.firstColor)?.setImageResource(R.drawable.checked)
            view?.findViewById<ImageView>(R.id.secondColor)
                ?.setImageResource(R.drawable.color_circle)
        }
        view?.findViewById<ImageView>(R.id.secondColor)?.setOnClickListener {
            view?.findViewById<ImageView>(R.id.firstColor)
                ?.setImageResource(R.drawable.color_circle)
            view?.findViewById<ImageView>(R.id.secondColor)?.setImageResource(R.drawable.checked)
        }

        view?.findViewById<TextView>(R.id.firstCapacity)?.setOnClickListener {
            view?.findViewById<TextView>(R.id.firstCapacity)?.backgroundTintList =
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.orange,
                        null
                    )
                )
            view?.findViewById<TextView>(R.id.secondCapacity)?.backgroundTintList =
                ColorStateList.valueOf(Color.TRANSPARENT)
            view?.findViewById<TextView>(R.id.firstCapacity)?.setTextColor(Color.WHITE)
            view?.findViewById<TextView>(R.id.secondCapacity)?.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.capacity_gray,
                    null
                )
            )
        }
        view?.findViewById<TextView>(R.id.secondCapacity)?.setOnClickListener {
            view?.findViewById<TextView>(R.id.firstCapacity)?.backgroundTintList =
                ColorStateList.valueOf(Color.TRANSPARENT)
            view?.findViewById<TextView>(R.id.secondCapacity)?.backgroundTintList =
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        requireContext().resources,
                        R.color.orange,
                        null
                    )
                )
            view?.findViewById<TextView>(R.id.firstCapacity)?.setTextColor(
                ResourcesCompat.getColor(
                    requireContext().resources,
                    R.color.capacity_gray,
                    null
                )
            )
            view?.findViewById<TextView>(R.id.secondCapacity)?.setTextColor(Color.WHITE)
        }

        if (details.isFavorite) {
            view?.findViewById<ImageButton>(R.id.favoriteButton)
                ?.setImageResource(R.drawable.favorite_icon_selected)
        } else {
            view?.findViewById<ImageButton>(R.id.favoriteButton)
                ?.setImageResource(R.drawable.favorite_icon_unselected)
        }
    }
}