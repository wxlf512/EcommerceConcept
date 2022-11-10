package dev.wxlf.feature.cart.domain.mappers

import android.icu.text.NumberFormat
import dev.wxlf.data.models.CartItemModel
import dev.wxlf.data.models.CartModel
import dev.wxlf.feature.cart.presentation.models.CartDisplayableModel
import dev.wxlf.feature.cart.presentation.models.CartItemDisplayableModel
import java.util.*

fun CartModel.mapToDisplayable(): CartDisplayableModel {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
    format.maximumFractionDigits = 0

    return CartDisplayableModel(
        basket = basket.map { it.mapToDisplayable() },
        delivery = delivery,
        total = "${format.format(total)} us"
    )
}

fun CartItemModel.mapToDisplayable(): CartItemDisplayableModel {

    return CartItemDisplayableModel(
        imageUrl = images,
        price = "$$price.00",
        title = title
    )
}