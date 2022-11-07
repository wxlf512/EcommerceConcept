package dev.wxlf.feature.explorer.presentation.adapters.items

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem

data class HotSalesItem(
    var label: String,
    var description: String,
    var isNew: Boolean,
    val imageUrl: String = ""
) : DisplayableItem
