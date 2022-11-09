package dev.wxlf.data.di

import dagger.Module
import dagger.Provides
import dev.wxlf.data.EcommerceApi
import dev.wxlf.data.remote.EcommerceRemoteDataSource
import dev.wxlf.data.remote.RetrofitEcommerceDataSource
import dev.wxlf.data.EcommerceRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideEcommerceApi(): EcommerceApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(EcommerceApi::class.java)
    }

    @Provides
    fun provideRetrofitDataSource(ecommerceApi: EcommerceApi): EcommerceRemoteDataSource =
        RetrofitEcommerceDataSource(ecommerceApi)

    @Provides
    fun provideEcommerceRepository(remoteDataSource: EcommerceRemoteDataSource): EcommerceRepository =
        EcommerceRepository(remoteDataSource)
}