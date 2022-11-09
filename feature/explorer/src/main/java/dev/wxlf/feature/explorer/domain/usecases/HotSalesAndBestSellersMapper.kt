package dev.wxlf.feature.explorer.domain.usecases

import android.icu.text.NumberFormat
import android.icu.util.Currency
import dev.wxlf.data.models.BestSeller
import dev.wxlf.data.models.HotSale
import dev.wxlf.data.models.HotSalesAndBestSellerModel
import dev.wxlf.feature.explorer.presentation.adapters.items.*

fun HotSalesAndBestSellerModel.mapToDisplayable() =
    listOf(
        HotSalesListItem(
            hotSales.map { it.mapToDisplayable() }
        ), TitleListItem("Best seller"),
        BestSellersListItem(
            bestSellers.map { it.mapToDisplayable() }
        )
    )

fun HotSale.mapToDisplayable() =
    HotSaleItem(
        label = title,
        description = subtitle,
        isNew = is_new,
        isBuy = is_buy,
        imageUrl = picture
    )

fun BestSeller.mapToDisplayable(): BestSellerItem {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("USD")

    return BestSellerItem(
        label = title,
        cost = format.format(price_without_discount),
        costWithoutDiscount = format.format(discount_price),
        isFavorite = is_favorites,
        imageUrl = picture
    )
}