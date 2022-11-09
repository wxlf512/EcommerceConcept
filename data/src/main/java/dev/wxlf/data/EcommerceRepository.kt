package dev.wxlf.data

import dev.wxlf.data.models.CartModel
import dev.wxlf.data.models.DetailsModel
import dev.wxlf.data.models.HotSalesAndBestSellerModel
import dev.wxlf.data.remote.EcommerceRemoteDataSource
import io.reactivex.rxjava3.core.Single

class EcommerceRepository(private val remoteDataSource: EcommerceRemoteDataSource) {
    fun fetchHotSalesAndBestSellers(): Single<HotSalesAndBestSellerModel> =
        remoteDataSource.loadHotSalesAndBestSeller()

    fun fetchDetails(): Single<DetailsModel> =
        remoteDataSource.loadDetails()

    fun fetchCart(): Single<CartModel> =
        remoteDataSource.loadCart()

}