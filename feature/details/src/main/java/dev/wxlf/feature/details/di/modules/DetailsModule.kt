package dev.wxlf.feature.details.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.wxlf.feature.details.presentation.DetailsFragment
import dev.wxlf.feature.details.presentation.DetailsViewModel

@Module
abstract class DetailsModule {

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment() : DetailsFragment

    @Binds
    internal abstract fun detailsViewModel(viewModel: DetailsViewModel) : ViewModel
}