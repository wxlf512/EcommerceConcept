package dev.wxlf.feature.explorer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.domain.usecases.FetchCartUseCase
import dev.wxlf.feature.explorer.domain.usecases.FetchHotSalesAndBestSellersUseCase
import dev.wxlf.feature.explorer.domain.usecases.mapToDisplayable
import dev.wxlf.feature.explorer.presentation.adapters.items.CategoriesListItem
import dev.wxlf.feature.explorer.presentation.adapters.items.CategoryItem
import dev.wxlf.feature.explorer.presentation.adapters.items.SearchListItem
import dev.wxlf.feature.explorer.presentation.adapters.items.TitleListItem
import dev.wxlf.feature.explorer.presentation.models.ExplorerEvent
import dev.wxlf.feature.explorer.presentation.models.ExplorerViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ExplorerViewModel @Inject constructor(
    private val fetchItemsDataUseCase: FetchHotSalesAndBestSellersUseCase,
    private val fetchCartGoodsCountUseCase: FetchCartUseCase
) : ViewModel() {

    private val _viewState: MutableLiveData<ExplorerViewState> = MutableLiveData()
    val viewState: LiveData<ExplorerViewState> = _viewState

    private val compositeDisposable = CompositeDisposable()

    fun obtainEvent(event: ExplorerEvent) {
        when (event) {
            ExplorerEvent.ScreenShown -> {
                _viewState.postValue(ExplorerViewState.LoadingState)
                loadData()
            }
        }
    }

    private fun loadData() {
        val items = mutableListOf(
            TitleListItem("Select category", "view all"),
            CategoriesListItem(
                listOf(
                    CategoryItem(
                        label = "Phones",
                        iconResId = R.drawable.category_icon_phones
                    ),
                    CategoryItem(
                        label = "Computer",
                        iconResId = R.drawable.category_icon_computer
                    ),
                    CategoryItem(
                        label = "Health",
                        iconResId = R.drawable.category_icon_health
                    ),
                    CategoryItem(
                        label = "Books",
                        iconResId = R.drawable.category_icon_books
                    ),
                    CategoryItem(
                        label = "Tools",
                        iconResId = R.drawable.category_icon_tools
                    )
                )
            ),
            SearchListItem(),
            TitleListItem("Hot sales")
        )

        val disposable =
            fetchItemsDataUseCase.execute().zipWith(fetchCartGoodsCountUseCase.execute()
            ) { p0, p1 -> Pair(p0, p1) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (itemsList, cart) ->
                    _viewState.postValue(
                        ExplorerViewState.LoadedState(
                            cart.basket.size,
                            listOf(
                                items,
                                itemsList.mapToDisplayable()
                            ).flatten()
                        )
                    )
                }, {
                    _viewState.postValue(ExplorerViewState.ErrorState(it.localizedMessage.orEmpty()))
                })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}