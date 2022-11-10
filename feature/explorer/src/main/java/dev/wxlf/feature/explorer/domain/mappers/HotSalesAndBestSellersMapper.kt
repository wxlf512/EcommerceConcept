package dev.wxlf.feature.explorer.domain.mappers

import android.icu.text.NumberFormat
import dev.wxlf.data.models.BestSeller
import dev.wxlf.data.models.HotSale
import dev.wxlf.data.models.HotSalesAndBestSellerModel
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.items.*
import java.util.*

fun HotSalesAndBestSellerModel.mapToDisplayable(): List<DisplayableItem> =
    listOf(
        HotSalesListItem(
            hotSales.map { it.mapToDisplayable() }
        ), TitleListItem("Best seller"),
        BestSellersListItem(
            bestSellers.map { it.mapToDisplayable() }
        )
    )

fun HotSale.mapToDisplayable(): HotSaleItem =
    HotSaleItem(
        label = title,
        description = subtitle,
        isNew = is_new,
        isBuy = is_buy,
        imageUrl = picture
    )

fun BestSeller.mapToDisplayable(): BestSellerItem {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
    format.maximumFractionDigits = 0

    return BestSellerItem(
        label = title,
        cost = format.format(price_without_discount),
        costWithoutDiscount = format.format(discount_price),
        isFavorite = is_favorites,
        imageUrl = picture
    )
}