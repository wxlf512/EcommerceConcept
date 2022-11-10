package dev.wxlf.feature.cart.domain.usecases

import dev.wxlf.data.EcommerceRepository
import dev.wxlf.data.models.CartModel
import io.reactivex.rxjava3.core.Single

class FetchCartUseCase(private val ecommerceRepository: EcommerceRepository) {
    fun execute(): Single<CartModel> =
        ecommerceRepository.fetchCart()
}