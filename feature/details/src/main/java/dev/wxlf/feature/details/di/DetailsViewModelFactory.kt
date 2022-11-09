package dev.wxlf.feature.details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.wxlf.feature.details.domain.usecases.FetchDetailsUseCase
import dev.wxlf.feature.details.presentation.DetailsViewModel

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val fetchDetailsUseCase: FetchDetailsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(fetchDetailsUseCase) as T
    }
}