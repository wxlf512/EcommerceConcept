package dev.wxlf.feature.explorer.presentation.adapters.items

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem

data class CategoryItem(
    var label: String,
    var iconResId: Int
) : DisplayableItem
