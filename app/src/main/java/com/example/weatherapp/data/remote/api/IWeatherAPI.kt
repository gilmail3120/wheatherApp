package com.example.weatherapp.data.remote.api

import com.example.weatherapp.domain.model.previsao5dias.Previsao5dias
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherAPI {

    @GET("forecast")
    suspend fun obterPrevisaoDias(@Query("q") busca:String, @Query("appid") apiKey:String): Response<Previsao5dias>

}