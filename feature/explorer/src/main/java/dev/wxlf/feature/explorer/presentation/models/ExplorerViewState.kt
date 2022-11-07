package dev.wxlf.feature.explorer.presentation.models

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem

sealed class ExplorerViewState {
    object LoadingState: ExplorerViewState()
    data class LoadedState(val goodsCount: Int, val data: List<DisplayableItem>): ExplorerViewState()
    data class ErrorState(val msg: String): ExplorerViewState()
}
