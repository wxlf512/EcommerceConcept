package dev.wxlf.ecommerceconcept.di

import dagger.Module
import dev.wxlf.feature.explorer.di.modules.ViewModelModule

@Module(includes = [ViewModelModule::class])
class AppModule {
}