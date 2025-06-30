package com.example.weatherapp.domain.model.previsaoagora

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PrevisaoAtual(
    val name: String,
    val dt: Int,
    val description: String,
    val temp: Double,
    val humidity: Int,
    val wind: Wind,
    val icon: String,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val visibility: Int,
    val sunrise: Int,
    val sunset: Int,
): Parcelable
