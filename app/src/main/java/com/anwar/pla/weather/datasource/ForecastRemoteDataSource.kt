package com.anwar.pla.weather.datasource

import com.anwar.pla.common.BaseDataSource
import com.anwar.pla.weather.remote.ForecastService

import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    private val forecastService: ForecastService
) : BaseDataSource() {
    suspend fun getWeatherForecast(latitude: Double, longitude: Double) =
        getResult { forecastService.getWeatherForecast(latitude, longitude) }
}