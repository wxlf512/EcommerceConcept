package dev.wxlf.feature.explorer.domain.usecases

import io.reactivex.rxjava3.core.Single

class FetchCartGoodsCountUseCase {

    fun execute(): Single<Int> =
        Single.just(2)
}