package dev.wxlf.feature.explorer.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.data.EcommerceRepository
import dev.wxlf.feature.explorer.domain.usecases.FetchCartUseCase
import dev.wxlf.feature.explorer.domain.usecases.FetchHotSalesAndBestSellersUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideFetchHotSalesUseCase(ecommerceRepository: EcommerceRepository): FetchHotSalesAndBestSellersUseCase =
        FetchHotSalesAndBestSellersUseCase(ecommerceRepository)

    @Provides
    fun fetchCartGoodsCountUseCase(ecommerceRepository: EcommerceRepository): FetchCartUseCase =
        FetchCartUseCase(ecommerceRepository)

}