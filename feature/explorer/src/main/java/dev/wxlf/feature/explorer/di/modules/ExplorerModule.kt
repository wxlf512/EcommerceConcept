package dev.wxlf.feature.explorer.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.wxlf.feature.explorer.presentation.ExplorerFragment
import dev.wxlf.feature.explorer.presentation.viewmodel.ExplorerViewModel

@Module(includes = [ViewModelModule::class, UseCaseModule::class])
abstract class ExplorerModule {

    @ContributesAndroidInjector
    abstract fun contributeExplorerFragment() : ExplorerFragment

    @Binds
    internal abstract fun explorerViewModel(viewModel: ExplorerViewModel) : ViewModel
}