package dev.wxlf.feature.explorer.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import dagger.android.support.AndroidSupportInjection
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.helpers.injectViewModel
import dev.wxlf.feature.explorer.presentation.adapters.AdapterDelegatesManager
import dev.wxlf.feature.explorer.presentation.adapters.CompositeAdapter
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.delegates.*
import dev.wxlf.feature.explorer.presentation.adapters.items.TitleListItem
import dev.wxlf.feature.explorer.presentation.models.ExplorerEvent
import dev.wxlf.feature.explorer.presentation.models.ExplorerViewState
import javax.inject.Inject

@ExperimentalBadgeUtils
class ExplorerFragment : Fragment(R.layout.fragment_explorer) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ExplorerViewModel

    private var dataLoaded = false
    private lateinit var adapter: CompositeAdapter<DisplayableItem>
    private lateinit var cartIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.background_white)
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.purple)
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val density = requireContext().resources.displayMetrics.density
        val topBar = view.findViewById<Toolbar>(R.id.explorerToolBar)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val bottomNavBar = view.findViewById<LinearLayout>(R.id.bottomNavView)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        cartIcon = bottomNavBar.findViewById(R.id.cartIcon)

        viewModel = injectViewModel(factory = viewModelFactory)
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ExplorerViewState.LoadingState -> {
                    progressBar.visibility = View.VISIBLE
                }
                is ExplorerViewState.LoadedState -> {
                    progressBar.visibility = View.GONE
                    adapter.setData(viewState.data)
                    cartIcon.viewTreeObserver.addOnGlobalLayoutListener(object :
                        ViewTreeObserver.OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            BadgeDrawable.create(requireContext()).apply {
                                number = viewState.goodsCount
                                this.backgroundColor = requireContext().getColor(R.color.orange)
                                BadgeUtils.attachBadgeDrawable(this, cartIcon)
                            }
                            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        }
                    })
                }
                is ExplorerViewState.ErrorState -> {
                    progressBar.visibility = View.GONE
                    adapter.setData(listOf(TitleListItem(viewState.msg, "")))
                }
            }
        }

        if (!dataLoaded) {
            dataLoaded = true
            viewModel.obtainEvent(ExplorerEvent.ScreenShown)
        }

        /*************** Cart icon navigation ***************/
        cartIcon.setOnClickListener {
            findNavController().navigate(Uri.parse("ecommerceapp://cart"),
                navOptions {
                    anim {
                    }
                })
        }

        /*************** Recycler view ***************/
        adapter = CompositeAdapter(
            AdapterDelegatesManager(
                TitleListItemAdapterDelegate(density),
                HotSalesListItemAdapterDelegate(density),
                CategoriesListItemAdapterDelegate(density),
                SearchListItemAdapterDelegate(),
                BestSellersListItemAdapterDelegate()
            ),
            items = listOf()
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (layoutManager.findFirstCompletelyVisibleItemPosition() > 0) {
                    topBar.elevation = 8 * requireContext().resources.displayMetrics.density
                } else {
                    topBar.elevation = 0f
                }
            }
        })
    }
}