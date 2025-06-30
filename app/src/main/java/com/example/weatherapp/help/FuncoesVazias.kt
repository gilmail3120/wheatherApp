package com.example.weatherapp.help

import com.example.weatherapp.domain.model.previsaoagora.PrevisaoAtual

object FuncoesVazias {
   fun previsaoVazia(): PrevisaoAtual {
        return PrevisaoAtual(
            name = "",
            dt = 0,
            description = "",
            temp = 0.0,
            humidity = 0,
            wind = com.example.weatherapp.domain.model.previsaoagora.Wind(0, 0.0, 0.0),
            icon = "",
            temp_min = 0.0,
            temp_max = 0.0,
            pressure = 0,
            visibility = 0,
            sunrise = 0,
            sunset = 0
        )
    }
}