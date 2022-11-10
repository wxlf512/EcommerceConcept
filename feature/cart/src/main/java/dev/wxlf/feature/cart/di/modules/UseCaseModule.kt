package dev.wxlf.feature.cart.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.data.EcommerceRepository
import dev.wxlf.feature.cart.domain.usecases.FetchCartUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideFetchCartUseCase(ecommerceRepository: EcommerceRepository): FetchCartUseCase =
        FetchCartUseCase(ecommerceRepository)
}