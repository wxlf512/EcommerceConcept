package dev.wxlf.feature.details.di.modules

import dagger.Module
import dagger.Provides
import dev.wxlf.feature.details.di.DetailsViewModelFactory
import dev.wxlf.feature.details.domain.usecases.FetchDetailsUseCase

@Module
class DetailsViewModelModule {

    @Provides
    internal fun providesViewModelFactory(
        fetchDetailsUseCase: FetchDetailsUseCase
    ) : DetailsViewModelFactory =
        DetailsViewModelFactory(fetchDetailsUseCase)
}