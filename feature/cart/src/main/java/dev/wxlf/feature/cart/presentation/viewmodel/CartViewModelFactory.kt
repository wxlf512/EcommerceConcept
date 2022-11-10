package dev.wxlf.feature.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.wxlf.feature.cart.domain.usecases.FetchCartUseCase

@Suppress("UNCHECKED_CAST")
class CartViewModelFactory(private val fetchCartUseCase: FetchCartUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(fetchCartUseCase) as T
    }
}