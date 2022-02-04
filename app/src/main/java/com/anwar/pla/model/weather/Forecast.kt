package com.anwar.pla.model.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val dt        : Long,
    val temp      : Temp,
    val weather   : List<Weather>,
    val sunrise   : Int,
    val sunset    : Int,
    val moonrise  : Int,
    val moonset   : Int,
    val moonPhase : Double,
    val feelsLike : FeelsLike,
    val pressure  : Int,
    val humidity  : Int,
    val dewPoint  : Double,
    val windSpeed : Double,
    val windDeg   : Int,
    val windGust  : Double,
    val clouds    : Int,
    val pop       : Double,
    val uvi       : Double
    ) : Parcelable
