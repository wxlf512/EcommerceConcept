package dev.wxlf.feature.details.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.feature.details.presentation.viewmodel.DetailsViewModelFactory
import dev.wxlf.feature.details.domain.usecases.FetchDetailsUseCase

@Module
class ViewModelModule {

    @Provides
    internal fun providesViewModelFactory(
        fetchDetailsUseCase: FetchDetailsUseCase
    ) : DetailsViewModelFactory =
        DetailsViewModelFactory(fetchDetailsUseCase)
}