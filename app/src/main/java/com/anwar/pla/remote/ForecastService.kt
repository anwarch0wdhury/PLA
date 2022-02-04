package com.anwar.pla.remote

import com.anwar.pla.common.Constants
import com.anwar.pla.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("onecall?")
    suspend fun getWeatherForecast(
        @Query("lat") latitude    : Double,
        @Query("lon") longitude   : Double,
        @Query("units") units     : String = Constants.UNITS,
        @Query("lang") language   : String = Constants.LANGUAGE,
        @Query("exclude") exclude : String = Constants.EXCLUDE,
        @Query("appid") appId     : String = Constants.API_KEY
    ) : Response<WeatherResponse>
}