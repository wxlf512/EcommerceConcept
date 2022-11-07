package dev.wxlf.feature.explorer.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.wxlf.feature.explorer.di.ViewModelKey
import dev.wxlf.feature.explorer.presentation.ExplorerFragment
import dev.wxlf.feature.explorer.presentation.ExplorerViewModel

@Module
abstract class ExplorerModule {

    @ContributesAndroidInjector
    abstract fun contributeExplorerFragment() : ExplorerFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExplorerViewModel::class)
    internal abstract fun explorerViewModel(viewModel: ExplorerViewModel) : ViewModel
}