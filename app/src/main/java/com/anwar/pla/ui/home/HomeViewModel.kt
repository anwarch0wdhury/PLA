package com.anwar.pla.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.anwar.pla.common.Resource
import com.anwar.pla.common.Utilities
import com.anwar.pla.data.WeatherResponse
import com.anwar.pla.ui.weather.ForecastRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ForecastRemoteDataSource) : ViewModel() {

    private val _forecastList = MutableLiveData<Resource<WeatherResponse>>()
    val weatherList: LiveData<Resource<WeatherResponse>>
        get() = _forecastList
    var address = MutableLiveData<String>()

    private var job: Job? = null

    fun setForecastList(latitude:Double, longitude:Double) {
        _forecastList.postValue(Resource.loading(data = null))
        job = viewModelScope.launch {
            while (isActive) {
                val response = repository.getWeatherForecast(latitude,longitude)
                withContext(Dispatchers.Main) {
                    _forecastList.postValue(response)
                }
                delay(3600000)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}