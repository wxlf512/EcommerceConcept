package dev.wxlf.ecommerceconcept.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.wxlf.data.di.DataComponent
import dev.wxlf.data.di.DataModule
import dev.wxlf.ecommerceconcept.App
import dev.wxlf.ecommerceconcept.di.modules.AppModule
import dev.wxlf.feature.details.di.DetailsComponent
import dev.wxlf.feature.details.di.modules.DetailsModule
import dev.wxlf.feature.details.di.modules.DetailsViewModelModule
import dev.wxlf.feature.explorer.di.ExplorerComponent
import dev.wxlf.feature.explorer.di.modules.ExplorerModule
import dev.wxlf.feature.explorer.di.modules.ExplorerViewModelModule


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ExplorerModule::class,
        ExplorerViewModelModule::class,
        dev.wxlf.feature.explorer.di.modules.UseCaseModule::class,
        DetailsModule::class,
        DetailsViewModelModule::class,
        dev.wxlf.feature.details.di.modules.UseCaseModule::class
    ],
    dependencies = [
        DataComponent::class,
        ExplorerComponent::class,
        DetailsComponent::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun dataComponent(dataComponent: DataComponent): Builder

        fun explorerComponent(explorerComponent: ExplorerComponent): Builder

        fun detailsComponent(detailsComponent: DetailsComponent): Builder

        fun build(): AppComponent
    }
}