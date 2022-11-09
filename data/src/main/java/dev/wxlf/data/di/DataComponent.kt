package dev.wxlf.data.di

import dagger.Component

@Component(modules = [DataModule::class])
@DataScope
interface DataComponent {

    @Component.Builder
    interface Builder {

        fun build(): DataComponent
    }
}