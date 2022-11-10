package dev.wxlf.feature.explorer.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.feature.explorer.domain.usecases.FetchCartUseCase
import dev.wxlf.feature.explorer.domain.usecases.FetchHotSalesAndBestSellersUseCase
import dev.wxlf.feature.explorer.presentation.viewmodel.ExplorerViewModelFactory

@Module
class ViewModelModule {

    @Provides
    internal fun providesViewModelFactory(
        fetchHotSalesAndBestSellersUseCase: FetchHotSalesAndBestSellersUseCase,
        fetchCartUseCase: FetchCartUseCase
    ) : ExplorerViewModelFactory =
        ExplorerViewModelFactory(fetchHotSalesAndBestSellersUseCase, fetchCartUseCase)
}