package dev.wxlf.feature.explorer.domain.usecases

import dev.wxlf.data.EcommerceRepository
import dev.wxlf.data.models.HotSalesAndBestSellerModel
import io.reactivex.rxjava3.core.Single

class FetchHotSalesAndBestSellersUseCase(private val ecommerceRepository: EcommerceRepository) {

    fun execute(): Single<HotSalesAndBestSellerModel> =
        ecommerceRepository.fetchHotSalesAndBestSellers()

}