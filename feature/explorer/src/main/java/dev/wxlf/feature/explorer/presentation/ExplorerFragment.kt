package dev.wxlf.feature.explorer.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
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
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.AdapterDelegatesManager
import dev.wxlf.feature.explorer.presentation.adapters.CompositeAdapter
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.delegates.*
import dev.wxlf.feature.explorer.presentation.models.ExplorerEvent
import dev.wxlf.feature.explorer.presentation.models.ExplorerViewState
import dev.wxlf.feature.explorer.presentation.viewmodel.ExplorerViewModel
import dev.wxlf.feature.explorer.presentation.viewmodel.ExplorerViewModelFactory
import javax.inject.Inject
import kotlin.math.roundToInt

@ExperimentalBadgeUtils
class ExplorerFragment : Fragment(R.layout.fragment_explorer) {

    @Inject
    lateinit var viewModelFactory: ExplorerViewModelFactory
    lateinit var viewModel: ExplorerViewModel

    private var dataLoaded = false
    private lateinit var adapter: CompositeAdapter<DisplayableItem>
    private lateinit var cartIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor =
            requireContext().getColor(R.color.background_white)
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.purple)
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.purple)
        super.onViewCreated(view, savedInstanceState)

        val density = requireContext().resources.displayMetrics.density
        val topBar = view.findViewById<Toolbar>(R.id.explorerToolBar)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val bottomNavBar = view.findViewById<LinearLayout>(R.id.bottomNavView)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        cartIcon = bottomNavBar.findViewById(R.id.cartIcon)

        viewModel = ViewModelProvider(this, viewModelFactory)[ExplorerViewModel::class.java]
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
                                horizontalOffset = (10 * density).roundToInt()
                                verticalOffset = (10 * density).roundToInt()
                                this.backgroundColor = requireContext().getColor(R.color.orange)
                                BadgeUtils.attachBadgeDrawable(this, cartIcon)
                            }
                            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        }
                    })
                }
                is ExplorerViewState.ErrorState -> {
                    progressBar.visibility = View.GONE
                    val errorView = view.findViewById<TextView>(R.id.errorTextView)
                    errorView.visibility = View.VISIBLE
                    errorView.text = "Error: ${viewState.msg}"
                }
            }
        }

        if (!dataLoaded) {
            dataLoaded = true
            viewModel.obtainEvent(ExplorerEvent.ScreenShown)
        }

        /*************** Filter ***************/
        val filterBottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        with(filterBottomSheetDialog) {
            setContentView(R.layout.filter_bottom_sheet_layout)

            findViewById<Spinner>(R.id.brandSpinner)?.adapter =
                createSpinnerAdapter(listOf("Samsung", "Xiaomi", "Apple", "Huawei"))
            findViewById<Spinner>(R.id.priceSpinner)?.adapter =
                createSpinnerAdapter(
                    listOf(
                        "$300 - $500",
                        "$501 - $1000",
                        "$1001 - $1500",
                        "$1501 - $2000"
                    )
                )
            findViewById<Spinner>(R.id.sizeSpinner)?.adapter =
                createSpinnerAdapter(listOf("4.5 to 5.5 inches", "5.5 to 6.5 inches", "6.5 to 7 inches"))

            findViewById<ImageButton>(R.id.closeFilterButton)?.setOnClickListener {
                dismiss()
            }
            findViewById<TextView>(R.id.doneFilterButton)?.setOnClickListener {
                dismiss()
            }
        }

        val filterButton: ImageView = view.findViewById(R.id.filterButton)
        filterButton.setOnClickListener {
            filterBottomSheetDialog.show()
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
        val bestSellerAdapterDelegate = BestSellersListItemAdapterDelegate()
        bestSellerAdapterDelegate.onBestSellerClick = {
            findNavController().navigate(Uri.parse("ecommerceapp://details"))
        }
        adapter = CompositeAdapter(
            AdapterDelegatesManager(
                TitleListItemAdapterDelegate(density),
                HotSalesListItemAdapterDelegate(density),
                CategoriesListItemAdapterDelegate(density),
                SearchListItemAdapterDelegate(),
                bestSellerAdapterDelegate
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

    private fun createSpinnerAdapter(items: List<String>): ArrayAdapter<String> {
        val arrAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            items
        )
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return arrAdapter
    }
}