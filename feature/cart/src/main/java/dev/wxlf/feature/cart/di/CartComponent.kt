package dev.wxlf.feature.cart.di

import dagger.Component
import dev.wxlf.feature.cart.di.modules.CartModule
import dev.wxlf.feature.cart.di.modules.UseCaseModule

@Component(modules = [CartModule::class, UseCaseModule::class])
@CartScope
interface CartComponent {

    @Component.Builder
    interface Builder {

        fun build(): CartComponent
    }
}