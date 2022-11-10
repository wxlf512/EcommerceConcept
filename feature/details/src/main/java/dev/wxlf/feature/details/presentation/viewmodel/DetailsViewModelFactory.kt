package dev.wxlf.feature.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.wxlf.feature.details.domain.usecases.FetchDetailsUseCase

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val fetchDetailsUseCase: FetchDetailsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(fetchDetailsUseCase) as T
    }
}