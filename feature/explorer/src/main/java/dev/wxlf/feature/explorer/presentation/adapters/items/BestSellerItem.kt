package dev.wxlf.feature.explorer.presentation.adapters.items

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem

data class BestSellerItem(
    var label: String,
    var cost: String,
    var costWithoutDiscount: String,
    var isFavorite: Boolean = false,
    var imageUrl: String = ""
) : DisplayableItem