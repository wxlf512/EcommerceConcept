package dev.wxlf.feature.explorer.presentation.adapters.items

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem

data class BestSellersListItem(
    var list: List<BestSellerItem>
) : DisplayableItem