package com.example.weatherapp.help

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Conversor {
    fun formaTempo(timesTamp: Int): String {
        val data = Date(timesTamp.toLong() * 1000)
        val formata = SimpleDateFormat("HH:mm a", Locale("pt", "BR"))
        return formata.format(data)
    }
    fun formataTempKelvinCelsius(kelvin:Double): String{
        val celsius = kelvin-273.15
        return String.format("%.1f°C", celsius)
    }

    fun formataDataSemana(timesTamp:Int):String{
        val data = Date(timesTamp.toLong()*1000)
        val formatada = SimpleDateFormat("EEEE", Locale("pt","BR"))
        return formatada.format(data).replaceFirstChar { it.uppercase() }
    }

    fun formataDataMes(timesTamp:Int):String{
        val data = Date(timesTamp.toLong()*1000)
        val formatada = SimpleDateFormat("dd  MMMM", Locale("pt","BR"))
        return formatada.format(data).replaceFirstChar { it.uppercase() }
    }

}
