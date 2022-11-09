package dev.wxlf.feature.details.di

import dagger.Component
import dev.wxlf.feature.details.di.modules.DetailsModule
import dev.wxlf.feature.details.di.modules.UseCaseModule

@Component(modules = [DetailsModule::class, UseCaseModule::class])
@DetailsScope
interface DetailsComponent {

    @Component.Builder
    interface Builder {

        fun build(): DetailsComponent
    }
}