package dev.wxlf.ecommerceconcept.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.wxlf.data.di.DataModule
import dev.wxlf.ecommerceconcept.App
import dev.wxlf.ecommerceconcept.di.modules.AppModule
import dev.wxlf.feature.cart.di.CartComponent
import dev.wxlf.feature.cart.di.modules.CartModule
import dev.wxlf.feature.details.di.DetailsComponent
import dev.wxlf.feature.details.di.modules.DetailsModule
import dev.wxlf.feature.explorer.di.ExplorerComponent
import dev.wxlf.feature.explorer.di.modules.ExplorerModule


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ExplorerModule::class,
        DetailsModule::class,
        CartModule::class
    ],
    dependencies = [
        ExplorerComponent::class,
        DetailsComponent::class,
        CartComponent::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun explorerComponent(explorerComponent: ExplorerComponent): Builder

        fun detailsComponent(detailsComponent: DetailsComponent): Builder

        fun cartComponent(cartComponent: CartComponent): Builder

        fun build(): AppComponent
    }
}