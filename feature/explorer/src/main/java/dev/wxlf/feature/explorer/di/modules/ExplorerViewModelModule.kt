package dev.wxlf.feature.explorer.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.feature.explorer.domain.usecases.FetchCartUseCase
import dev.wxlf.feature.explorer.domain.usecases.FetchHotSalesAndBestSellersUseCase

@Module
class ExplorerViewModelModule {

    @Provides
    internal fun providesViewModelFactory(
        fetchHotSalesAndBestSellersUseCase: FetchHotSalesAndBestSellersUseCase,
        fetchCartUseCase: FetchCartUseCase
    ) : ExplorerViewModelFactory =
        ExplorerViewModelFactory(fetchHotSalesAndBestSellersUseCase, fetchCartUseCase)
}