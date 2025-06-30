package com.example.weatherapp.data.remote.repository

import android.util.Log
import com.example.weatherapp.data.remote.api.IWeatherAPI
import com.example.weatherapp.domain.model.ApiKey
import com.example.weatherapp.domain.model.Linguagem
import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoesHoras
import com.example.weatherapp.domain.model.previsao5dias.WheatherPrevisoesDias
import com.example.weatherapp.domain.model.previsaoagora.PrevisaoAtual
import com.example.weatherapp.help.FuncoesVazias
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(val weatherAPI: IWeatherAPI, val apiKey: ApiKey,val linguagem: Linguagem) :
    IWeatherRepository {
    override suspend fun obterPrevisaoHoras(cidade: String): List<WeatherPrevisoesHoras> {
        try {
            val response = weatherAPI.obterPrevisaoDias(cidade,apiKey.value,linguagem.value)
            Log.i("resposta", "repository:$response")
            if (response.isSuccessful) {
                return response.body()?.list?.map { retorno ->
                    Log.i("resposta", "repositoryHoras:$retorno")
                    val data = retorno.dt
                    val temp = retorno.main.temp
                    val descricao = retorno.clouds.toString()
                    val imageUrl =
                        "https://openweathermap.org/img/wn/${retorno.weather.firstOrNull()?.icon}@2x.png"
                    WeatherPrevisoesHoras(
                        data,
                        temp,
                        descricao,
                        imageUrl
                    )
                } ?: emptyList()
            }
        } catch (erro: Exception) {
            erro.printStackTrace()
        }
        return emptyList()
    }

    override suspend fun obterPrevisaoDias(cidade: String): List<WheatherPrevisoesDias> {
        try {
            val response = weatherAPI.obterPrevisaoDias(cidade, apiKey.value,linguagem.value)
            if (response.isSuccessful) {

                return response.body()?.list?.map { retorno ->
                    Log.i("respostaDias", "repositoryDias:$retorno")
                    val wheather = retorno.main
                    val date = retorno.dt
                    val status = retorno.weather[0].description
                    val temp_max = wheather.temp_max
                    val temp_min = wheather.temp_min
                    val iconUrl =
                        "https://openweathermap.org/img/wn/${retorno.weather.firstOrNull()?.icon}@2x.png"
                    WheatherPrevisoesDias(date, iconUrl, status.toString(), temp_max, temp_min)
                } ?: emptyList()
            }

        } catch (erro: Exception) {
            erro.printStackTrace()
            Log.i("respostaDias", "repositoryDias:$erro ")
        }
        return emptyList()
    }

    override suspend fun obterPrevisaoAtual(cidade: String): PrevisaoAtual {
        try {
            val response = weatherAPI.obterPrevisaoAtual(cidade, apiKey.value,linguagem.value)
            val resultado = response.body()
            if (response.isSuccessful && response != null && resultado != null) {
                Log.i("respostaAtual", "ObserveDias:$resultado")
                val name = resultado.name
                val data = resultado.dt
                val descricao = resultado.weather[0].description
                val temp = resultado.main.temp
                val humidade = resultado.main.humidity
                val vento = resultado.wind
                val imageUrl = "https://openweathermap.org/img/wn/${resultado.weather.firstOrNull()?.icon}@2x.png"
                val temp_min= resultado.main.temp_min
                val temp_max=resultado.main.temp_max
                val pressure= resultado.main.pressure
                val visibility= resultado.visibility
                val sunrise= resultado.sys.sunrise
                val sunset =resultado.sys.sunset
                return PrevisaoAtual(name,data,descricao.toString(),temp,humidade,vento,imageUrl,temp_min,temp_max,pressure,visibility,sunrise,sunset)
            }

        } catch (erro: Exception) {
            erro.printStackTrace()
        }
        return FuncoesVazias.previsaoVazia()
    }
}