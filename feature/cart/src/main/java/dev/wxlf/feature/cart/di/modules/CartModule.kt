package dev.wxlf.feature.cart.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.wxlf.feature.cart.presentation.CartFragment
import dev.wxlf.feature.cart.presentation.viewmodel.CartViewModel

@Module(includes = [ViewModelModule::class, UseCaseModule::class])
abstract class CartModule {

    @ContributesAndroidInjector
    abstract fun contributeCartFragment() : CartFragment

    @Binds
    internal abstract fun detailsViewModel(viewModel: CartViewModel) : ViewModel
}