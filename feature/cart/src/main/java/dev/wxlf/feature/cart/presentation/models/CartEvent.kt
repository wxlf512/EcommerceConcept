package dev.wxlf.feature.cart.presentation.models

sealed class CartEvent {
    object ScreenShown : CartEvent()
}
