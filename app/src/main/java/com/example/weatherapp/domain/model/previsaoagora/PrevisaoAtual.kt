package com.example.weatherapp.domain.model.previsaoagora

data class PrevisaoAtual(
    val name: String,
    val dt: Int,
    val description: String,
    val temp: Double,
    val humidity: Int,
    val wind: Wind,
    val icon: String,
)
