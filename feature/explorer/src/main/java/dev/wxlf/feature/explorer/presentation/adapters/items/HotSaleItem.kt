package dev.wxlf.feature.explorer.presentation.adapters.items

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem

data class HotSaleItem(
    var label: String,
    var description: String,
    var isNew: Boolean,
    var isBuy: Boolean,
    val imageUrl: String = ""
) : DisplayableItem
