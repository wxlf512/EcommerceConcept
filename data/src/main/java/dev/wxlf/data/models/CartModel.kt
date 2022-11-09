package dev.wxlf.data.models

import com.google.gson.annotations.SerializedName

data class CartModel(
    @SerializedName("basket")
    val basket: List<Basket>,

    @SerializedName("delivery")
    val delivery: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("total")
    val total: Int
)

data class Basket(
    @SerializedName("id")
    val id: Int,

    @SerializedName("images")
    val images: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("title")
    val title: String
)