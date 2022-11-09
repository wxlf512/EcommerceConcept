package dev.wxlf.data.remote

import dev.wxlf.data.EcommerceApi
import dev.wxlf.data.models.CartModel
import dev.wxlf.data.models.DetailsModel
import dev.wxlf.data.models.HotSalesAndBestSellerModel
import io.reactivex.rxjava3.core.Single

class RetrofitEcommerceDataSource(private val ecommerceApi: EcommerceApi) : EcommerceRemoteDataSource{
    override fun loadHotSalesAndBestSeller(): Single<HotSalesAndBestSellerModel> =
        ecommerceApi.loadHotSalesAndBestSeller()

    override fun loadDetails(): Single<DetailsModel> =
        ecommerceApi.loadDetails()

    override fun loadCart(): Single<CartModel> =
        ecommerceApi.loadCart()
}