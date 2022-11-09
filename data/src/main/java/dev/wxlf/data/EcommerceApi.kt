package dev.wxlf.data

import dev.wxlf.data.models.CartModel
import dev.wxlf.data.models.DetailsModel
import dev.wxlf.data.models.HotSalesAndBestSellerModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface EcommerceApi {

    @GET("./654bd15e-b121-49ba-a588-960956b15175")
    fun loadHotSalesAndBestSeller(): Single<HotSalesAndBestSellerModel>

    @GET("./6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    fun loadDetails(): Single<DetailsModel>

    @GET("./53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    fun loadCart(): Single<CartModel>
}