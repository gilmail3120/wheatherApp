package com.example.weatherapp.domain.model.previsao5dias

data class WeatherPrevisoes(
    val date: Int,
    val temperature: Double,
    val description: String,
    val iconUrl: String? = null
)
