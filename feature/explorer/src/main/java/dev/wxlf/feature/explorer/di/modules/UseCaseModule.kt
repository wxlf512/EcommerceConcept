package dev.wxlf.feature.explorer.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.feature.explorer.domain.usecases.FetchCartGoodsCountUseCase
import dev.wxlf.feature.explorer.domain.usecases.FetchItemsDataUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideFetchHotSalesUseCase(): FetchItemsDataUseCase = FetchItemsDataUseCase()

    @Provides
    fun fetchCartGoodsCountUseCase(): FetchCartGoodsCountUseCase = FetchCartGoodsCountUseCase()

}