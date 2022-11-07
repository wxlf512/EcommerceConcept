package dev.wxlf.feature.explorer.presentation.adapters.items

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem

data class TitleListItem(
    var title: String,
    var seemore: String = "see more"
) : DisplayableItem
