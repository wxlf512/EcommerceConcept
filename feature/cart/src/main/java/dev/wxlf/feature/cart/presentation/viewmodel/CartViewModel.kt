package dev.wxlf.feature.cart.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.wxlf.feature.cart.domain.mappers.mapToDisplayable
import dev.wxlf.feature.cart.domain.usecases.FetchCartUseCase
import dev.wxlf.feature.cart.presentation.models.CartEvent
import dev.wxlf.feature.cart.presentation.models.CartViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CartViewModel @Inject constructor(private val fetchCartUseCase: FetchCartUseCase) : ViewModel(){

    private val _viewState: MutableLiveData<CartViewState> = MutableLiveData()
    val viewState: LiveData<CartViewState> = _viewState

    private val compositeDisposable = CompositeDisposable()

    fun obtainEvent(event: CartEvent) {
        when(event) {
            CartEvent.ScreenShown -> {
                _viewState.postValue(CartViewState.LoadingState)
                loadCart()
            }
        }
    }

    private fun loadCart() {
        val disposable = fetchCartUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _viewState.postValue(CartViewState.LoadedState(it.mapToDisplayable()))
            }, {
                _viewState.postValue(CartViewState.ErrorState(it.localizedMessage.orEmpty()))
            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}