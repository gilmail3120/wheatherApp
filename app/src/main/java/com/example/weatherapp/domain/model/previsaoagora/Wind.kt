package com.example.weatherapp.domain.model.previsaoagora

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
): Parcelable