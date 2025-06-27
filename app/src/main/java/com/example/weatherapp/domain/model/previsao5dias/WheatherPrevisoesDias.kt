package com.example.weatherapp.domain.model.previsao5dias

data class WheatherPrevisoesDias(
    val date: Int,
    val iconUrl: String? = null,
    val main: String,
    val temp_max: Double,
    val temp_min: Double
)
