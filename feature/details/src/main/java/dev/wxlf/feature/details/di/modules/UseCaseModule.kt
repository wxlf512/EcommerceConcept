package dev.wxlf.feature.details.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.data.EcommerceRepository
import dev.wxlf.feature.details.domain.usecases.FetchDetailsUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideFetchDetailsUseCase(ecommerceRepository: EcommerceRepository): FetchDetailsUseCase =
        FetchDetailsUseCase(ecommerceRepository)
}