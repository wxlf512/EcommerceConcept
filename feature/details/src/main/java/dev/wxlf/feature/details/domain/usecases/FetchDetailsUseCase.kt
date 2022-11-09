package dev.wxlf.feature.details.domain.usecases

import dev.wxlf.data.EcommerceRepository
import dev.wxlf.data.models.DetailsModel
import io.reactivex.rxjava3.core.Single

class FetchDetailsUseCase(private val ecommerceRepository: EcommerceRepository) {
    fun execute(): Single<DetailsModel> =
        ecommerceRepository.fetchDetails()
}