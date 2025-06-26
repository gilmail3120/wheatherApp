package com.example.weatherapp.domain.model.previsaoagora

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)