package dev.wxlf.data.di

import dagger.Component

@Component(modules = [DataModule::class])
interface DataComponent {

    @Component.Builder
    interface Builder {

        fun build(): DataComponent
    }
}