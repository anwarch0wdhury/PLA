package com.anwar.pla.home.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.lifecycle.*
import com.anwar.pla.MainApp
import com.anwar.pla.common.Resource
import com.anwar.pla.common.Utilities
import com.anwar.pla.namaj.data.NamajResponse
import com.anwar.pla.weather.data.WeatherResponse
import com.anwar.pla.namaj.datasource.NamajRemoteDataSource
import com.anwar.pla.weather.datasource.ForecastRemoteDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@ObsoleteCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val repositoryWeather: ForecastRemoteDataSource, private val repositoryNamaj: NamajRemoteDataSource) : ViewModel() {

    private val _forecastList = MutableLiveData<Resource<WeatherResponse>>()
    val weatherList: LiveData<Resource<WeatherResponse>> = _forecastList

    private val _namajList = MutableLiveData<Resource<NamajResponse>>()
    val namajList: LiveData<Resource<NamajResponse>> = _namajList

    var address = MutableLiveData<String>()

    val current_day   = MutableLiveData("25, jan")
    val current_time  = MutableLiveData("00:00 am")

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var job: Job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    init {
        setTimeAndDate()
    }

    @SuppressLint("MissingPermission")
    fun location(context: Context){
        if (MainApp.locationActive){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location ->
                val geocoder = Geocoder( context, Locale.getDefault())
                val addresses: List<Address> = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1);
                val loc_address = addresses.get(0)
                val area = loc_address.getAddressLine(0)
                address = MutableLiveData("$area")
                setForecastList(location.latitude,location.longitude)
                 }
            }
    }

    private fun setTimeAndDate(){
        job = scope.launch(newSingleThreadContext("Update time")) {
            while (isActive){
                withContext(Dispatchers.Main) {
                    current_day.value  = Utilities.day()
                    current_time.value = Utilities.time()
                }
            }
        }
    }

    private fun setForecastList(latitude:Double, longitude:Double) {
        _forecastList.postValue(Resource.loading(data = null))
        job = scope.launch(newSingleThreadContext("Update weather")) {
            while (isActive) {
                val response = repositoryWeather.getWeatherForecast(latitude,longitude)
                withContext(Dispatchers.Main) {
                    _forecastList.postValue(response)
                    Log.e("weather response",_forecastList.value.toString())
                }
                setNamajList(Utilities.dateForNamajToString(),latitude,longitude)
                delay(360000)

            }
        }
    }

    private fun setNamajList(date: String,latitude:Double, longitude:Double) {
        _namajList.postValue(Resource.loading(data = null))
        job = scope.launch(newSingleThreadContext("Update namaj")) {
                val response = repositoryNamaj.getNamajTiming(date,latitude,longitude)
                withContext(Dispatchers.Main) {
                    _namajList.postValue(response)
                    Log.e("namaj response",_namajList.value.toString())
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}