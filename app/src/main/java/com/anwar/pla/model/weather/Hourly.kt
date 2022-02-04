package com.anwar.pla.model.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hourly (
    val dt         : Long,
    val temp       : Double,
    val feels_like : Double,
    val pressure   : Int,
    val humidity   : Int,
    val dew_Point  : Double,
    val clouds     : Int,
    val visibility : Int,
    val wind_speed : Double,
    val wind_deg   : Int,
    val wind_gust  : Double,
    val weather    : List<Weather>,
    val pop        : Double
    ) : Parcelable