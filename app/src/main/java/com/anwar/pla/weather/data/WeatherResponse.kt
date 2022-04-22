package com.anwar.pla.weather.data

import android.os.Parcelable
import com.anwar.pla.weather.model.Alerts
import com.anwar.pla.weather.model.Current
import com.anwar.pla.weather.model.Forecast
import com.anwar.pla.weather.model.Hourly
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    val lat            : Double,
    val lon            : Double,
    val timezone       : String,
    val timezoneOffset : Int,
    val current        : Current,
    val hourly         : List<Hourly>,
    val daily          : List<Forecast>,
    val alerts         : List<Alerts>

) : Parcelable
