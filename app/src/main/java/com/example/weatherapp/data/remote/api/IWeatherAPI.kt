package com.example.weatherapp.data.remote.api

import com.example.weatherapp.domain.model.ApiKey
import com.example.weatherapp.domain.model.Linguagem
import com.example.weatherapp.domain.model.previsao5dias.Previsao5dias
import com.example.weatherapp.domain.model.previsaoagora.CurrentTime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherAPI {

    @GET("forecast")
    suspend fun obterPrevisaoDias(@Query("q") busca:String, @Query("appid") apiKey: String, @Query("lang") linguagem: String): Response<Previsao5dias>

    @GET("weather")
    suspend fun obterPrevisaoAtual(@Query("q") busca:String, @Query("appid") apiKey: String, @Query("lang") linguagem: String): Response<CurrentTime>
}