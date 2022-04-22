package com.anwar.pla.weather.remote
import com.anwar.pla.common.Constants
import com.anwar.pla.weather.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("onecall?")
    suspend fun getWeatherForecast(
        @Query("lat") latitude    : Double,
        @Query("lon") longitude   : Double,
        @Query("units") units     : String = Constants.WEATHER_UNITS,
        @Query("lang") language   : String = Constants.WEATHER_LANGUAGE,
        @Query("exclude") exclude : String = Constants.WEATHER_EXCLUDE,
        @Query("appid") appId     : String = Constants.WEATHER_API_KEY
    ) : Response<WeatherResponse>
}