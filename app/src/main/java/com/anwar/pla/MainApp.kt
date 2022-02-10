package com.anwar.pla

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.app.ActivityCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application(){

    companion object {
        lateinit var instance  : Application
        lateinit var resourses : Resources
        var locationActive : Boolean = false
        var internetActive : Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        instance  = this
        resourses = resources
        locationActive  = isLocationAvailable()
        internetActive  = isInternetAvailable()
    }

     private fun isLocationAvailable(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        } else {
            val locationManager =
                this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val internetStatus =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return  when {
            internetStatus.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            internetStatus.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            internetStatus.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}