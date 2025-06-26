package com.example.weatherapp.data.remote.repository

import android.util.Log
import com.example.weatherapp.data.remote.api.IWeatherAPI
import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoes
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(val weatherAPI: IWeatherAPI, val apiKey: String) :
    IWeatherRepository {
    override suspend fun obterPrevisao5Dias(cidade: String): List<WeatherPrevisoes> {
        try {
            val response = weatherAPI.obterPrevisaoDias(cidade, apiKey)
            Log.i("resposta", "repository:$response")
            if (response.isSuccessful) {
                return response.body()?.list?.map { retorno ->
                    Log.i("resposta", "repository:$retorno")
                    val data = retorno.dt
                    val temp = retorno.main.temp
                    val descricao = retorno.clouds.toString()
                    WeatherPrevisoes(
                        data,
                        temp,
                        descricao,
                        "https://openweathermap.org/img/wn/${retorno.weather.firstOrNull()?.icon}@2x.png"
                    )
                } ?: emptyList()
            }
        } catch (erro: Exception) {
            erro.printStackTrace()
            Log.i("resposta", "repository:$erro ")
        }
        return emptyList()
    }
}