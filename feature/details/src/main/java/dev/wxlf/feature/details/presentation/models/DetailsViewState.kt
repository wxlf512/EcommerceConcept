package dev.wxlf.feature.details.presentation.models

sealed class DetailsViewState {
    object LoadingState: DetailsViewState()
    data class LoadedState(val data: DetailsDisplayableModel): DetailsViewState()
    data class ErrorState(val msg: String): DetailsViewState()
}