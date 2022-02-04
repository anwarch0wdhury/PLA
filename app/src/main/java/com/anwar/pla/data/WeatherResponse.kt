package com.anwar.pla.data

import android.os.Parcelable
import com.anwar.pla.model.weather.Alerts
import com.anwar.pla.model.weather.Current
import com.anwar.pla.model.weather.Forecast
import com.anwar.pla.model.weather.Hourly
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
