package com.anwar.pla.weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeelsLike (
   var day   : Double,
   var night : Double,
   var eve   : Double,
   var morn  : Double
   ) : Parcelable