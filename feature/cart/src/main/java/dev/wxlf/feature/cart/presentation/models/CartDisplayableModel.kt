package dev.wxlf.feature.cart.presentation.models

data class CartDisplayableModel(
    var basket: List<CartItemDisplayableModel>,
    var delivery: String,
    var total: String
)
