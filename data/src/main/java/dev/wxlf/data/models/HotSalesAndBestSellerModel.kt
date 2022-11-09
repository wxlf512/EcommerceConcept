package dev.wxlf.data.models

import com.google.gson.annotations.SerializedName

data class HotSalesAndBestSellerModel(
    @SerializedName("home_store")
    val hotSales: List<HotSale>,

    @SerializedName("best_seller")
    val bestSellers: List<BestSeller>
)

data class HotSale(
    @SerializedName("id")
    val id: Int,

    @SerializedName("is_new")
    val is_new: Boolean = false,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("picture")
    val picture: String,

    @SerializedName("is_buy")
    val is_buy: Boolean
)

data class BestSeller(
    @SerializedName("id")
    val id: Int,

    @SerializedName("is_favorites")
    val is_favorites: Boolean,

    @SerializedName("title")
    val title: String,

    @SerializedName("price_without_discount")
    val price_without_discount: Int,

    @SerializedName("discount_price")
    val discount_price: Int,

    @SerializedName("picture")
    val picture: String
)