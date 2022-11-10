package dev.wxlf.feature.cart.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.feature.cart.domain.usecases.FetchCartUseCase
import dev.wxlf.feature.cart.presentation.viewmodel.CartViewModelFactory

@Module
class ViewModelModule {

    @Provides
    internal fun providesViewModelFactory(
        fetchCartUseCase: FetchCartUseCase
    ) : CartViewModelFactory =
        CartViewModelFactory(fetchCartUseCase)
}