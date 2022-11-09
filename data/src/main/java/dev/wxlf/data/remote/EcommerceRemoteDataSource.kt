package dev.wxlf.data.remote

import dev.wxlf.data.models.CartModel
import dev.wxlf.data.models.DetailsModel
import dev.wxlf.data.models.HotSalesAndBestSellerModel
import io.reactivex.rxjava3.core.Single

interface EcommerceRemoteDataSource {

    fun loadHotSalesAndBestSeller(): Single<HotSalesAndBestSellerModel>

    fun loadDetails(): Single<DetailsModel>

    fun loadCart(): Single<CartModel>
}