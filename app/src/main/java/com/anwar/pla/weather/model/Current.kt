package com.anwar.pla.weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Current (
   val dt         : Long,
   val sunrise    : Int,
   val sunset     : Int,
   val temp       : Double,
   val feels_like : Double,
   val pressure   : Int,
   val humidity   : Int,
   val dewPoint   : Double,
   val clouds     : Int,
   val visibility : Int,
   val wind_speed : Double,
   val wind_deg   : Int,
   val wind_gust  : Double,
   val weather    : List<Weather>
   ) : Parcelable