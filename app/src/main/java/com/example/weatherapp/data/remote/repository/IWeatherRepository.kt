package com.example.weatherapp.data.remote.repository

import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoesHoras
import com.example.weatherapp.domain.model.previsao5dias.WheatherPrevisoesDias
import com.example.weatherapp.domain.model.previsaoagora.CurrentTime
import com.example.weatherapp.domain.model.previsaoagora.PrevisaoAtual

interface IWeatherRepository {
    suspend fun obterPrevisaoHoras(cidade:String): List<WeatherPrevisoesHoras>
    suspend fun obterPrevisaoDias(cidade:String): List<WheatherPrevisoesDias>
    suspend fun obterPrevisaoAtual(cidade:String): PrevisaoAtual
}