package dev.wxlf.ecommerceconcept.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.wxlf.data.di.DataComponent
import dev.wxlf.data.di.DataModule
import dev.wxlf.ecommerceconcept.App
import dev.wxlf.feature.explorer.di.ExplorerComponent
import dev.wxlf.feature.explorer.di.modules.ExplorerModule
import dev.wxlf.feature.explorer.di.modules.UseCaseModule
import dev.wxlf.feature.explorer.di.modules.ViewModelModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ExplorerModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        DataModule::class
    ],
    dependencies = [
        ExplorerComponent::class,
        DataComponent::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun explorerComponent(explorerComponent: ExplorerComponent): Builder

        fun dataComponent(dataComponent: DataComponent): Builder

        fun build(): AppComponent
    }
}