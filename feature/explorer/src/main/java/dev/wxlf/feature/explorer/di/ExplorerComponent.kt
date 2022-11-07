package dev.wxlf.feature.explorer.di

import dagger.Component
import dev.wxlf.feature.explorer.di.modules.ExplorerModule
import dev.wxlf.feature.explorer.di.modules.UseCaseModule

@Component(modules = [ExplorerModule::class, UseCaseModule::class])
@ExplorerScope
interface ExplorerComponent {

    @Component.Builder
    interface Builder {

        fun build(): ExplorerComponent
    }
}