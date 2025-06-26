package com.example.weatherapp.data.remote.repository

import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoes

interface IWeatherRepository {
    suspend fun obterPrevisao5Dias(cidade:String): List<WeatherPrevisoes>
}