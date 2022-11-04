package dev.wxlf.ecommerceconcept

import android.app.Application
import dev.wxlf.ecommerceconcept.di.AppComponent
import dev.wxlf.ecommerceconcept.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        daggerConfiguration()
    }

    private fun daggerConfiguration() {
        appComponent = DaggerAppComponent.create()
    }

}