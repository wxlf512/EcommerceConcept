package dev.wxlf.feature.details.presentation.models

data class DetailsDisplayableModel(
    var imagesUrls: List<String>,
    var title: String,
    var rating: Double,
    var isFavorite: Boolean,
    var cpu: String,
    var camera: String,
    var ram: String,
    var memory: String,
    var colors: List<String>,
    var capacity: List<Int>,
    var price: String
)
