package com.anwar.pla.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.*
import com.anwar.pla.MainApp
import com.anwar.pla.common.Resource
import com.anwar.pla.common.Utilities
import com.anwar.pla.data.WeatherResponse
import com.anwar.pla.ui.weather.ForecastRemoteDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject


@ObsoleteCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ForecastRemoteDataSource) : ViewModel() {

    private val _forecastList = MutableLiveData<Resource<WeatherResponse>>()
    val weatherList: LiveData<Resource<WeatherResponse>>
        get() = _forecastList
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
                val country = loc_address.countryName
                val area = loc_address.adminArea

                address = MutableLiveData("$area, $country")
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
                val response = repository.getWeatherForecast(latitude,longitude)
                withContext(Dispatchers.Main) {
                    _forecastList.postValue(response)
                }
                delay(360000)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}