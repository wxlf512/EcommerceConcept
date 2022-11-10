package dev.wxlf.ecommerceconcept

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.wxlf.data.di.DaggerDataComponent
import dev.wxlf.data.di.DataComponent
import dev.wxlf.ecommerceconcept.di.DaggerAppComponent
import dev.wxlf.feature.cart.di.CartComponent
import dev.wxlf.feature.cart.di.DaggerCartComponent
import dev.wxlf.feature.details.di.DaggerDetailsComponent
import dev.wxlf.feature.details.di.DetailsComponent
//import dev.wxlf.ecommerceconcept.di.DaggerAppComponent
import dev.wxlf.feature.explorer.di.DaggerExplorerComponent
import dev.wxlf.feature.explorer.di.ExplorerComponent
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .dataComponent(provideDataComponent())
            .explorerComponent(provideExplorerComponent())
            .detailsComponent(provideDetailsComponent())
            .cartComponent(provideCartComponent())
            .build()

        appComponent.inject(this)

    }

    private fun provideDataComponent(): DataComponent =
        DaggerDataComponent
            .builder()
            .build()

    private fun provideExplorerComponent(): ExplorerComponent =
        DaggerExplorerComponent
            .builder()
            .build()

    private fun provideDetailsComponent(): DetailsComponent =
        DaggerDetailsComponent
            .builder()
            .build()

    private fun provideCartComponent(): CartComponent =
        DaggerCartComponent
        .builder()
        .build()
}