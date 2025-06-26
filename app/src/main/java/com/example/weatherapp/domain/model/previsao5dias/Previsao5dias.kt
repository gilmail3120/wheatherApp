package com.example.weatherapp.domain.model.previsao5dias

data class Previsao5dias(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item7>,
    val message: Int
)