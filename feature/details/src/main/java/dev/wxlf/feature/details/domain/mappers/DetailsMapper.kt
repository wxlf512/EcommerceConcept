package dev.wxlf.feature.details.domain.mappers

import android.icu.text.NumberFormat
import dev.wxlf.data.models.DetailsModel
import dev.wxlf.feature.details.presentation.models.DetailsDisplayableModel
import java.util.*

fun DetailsModel.mapToDisplayable(): DetailsDisplayableModel {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
    format.minimumFractionDigits = 2
    format.maximumFractionDigits = 2

    return DetailsDisplayableModel(
        imagesUrls = images,
        title = title,
        rating = rating,
        isFavorite = isFavorites,
        cpu = CPU,
        camera = camera,
        ram = ssd,
        memory = sd,
        colors = color,
        capacity = capacity,
        price = format.format(price)
    )
}