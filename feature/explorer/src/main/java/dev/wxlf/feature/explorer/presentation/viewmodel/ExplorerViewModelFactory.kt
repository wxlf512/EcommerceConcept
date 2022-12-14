package dev.wxlf.feature.explorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.wxlf.feature.explorer.domain.usecases.FetchCartUseCase
import dev.wxlf.feature.explorer.domain.usecases.FetchHotSalesAndBestSellersUseCase

@Suppress("UNCHECKED_CAST")
class ExplorerViewModelFactory(
    private val fetchHotSalesAndBestSellersUseCase: FetchHotSalesAndBestSellersUseCase,
    private val fetchCartUseCase: FetchCartUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExplorerViewModel(fetchHotSalesAndBestSellersUseCase, fetchCartUseCase) as T
    }
}