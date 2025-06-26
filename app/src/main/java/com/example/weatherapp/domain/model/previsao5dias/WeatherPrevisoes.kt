package com.example.weatherapp.domain.model.previsao5dias

data class WeatherPrevisoes(
    val date: String,
    val temperature: Double,
    val description: String,
    val iconUrl: String? = null
)
