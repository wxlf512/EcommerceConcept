package dev.wxlf.feature.cart.presentation.models

sealed class CartViewState {
    object LoadingState : CartViewState()
    data class LoadedState(var data: CartDisplayableModel) : CartViewState()
    data class ErrorState(var msg: String) : CartViewState()
}