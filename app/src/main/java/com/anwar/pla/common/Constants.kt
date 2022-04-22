package com.anwar.pla.common

import com.anwar.pla.BuildConfig

object Constants {
    const val WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val WEATHER_API_KEY  = BuildConfig.WEATHER_API_KEY
    const val WEATHER_UNITS    = "metric"
    const val WEATHER_LANGUAGE = "en"
    const val WEATHER_EXCLUDE  = "minutely"

    const val NAMAJ_BASE_URL = "http://api.aladhan.com/v1/timings/"
    const val NAMAJ_METHOD   = 3
}