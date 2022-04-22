package com.anwar.pla.weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alerts (
   val senderName  : String,
   val event       : String,
   val start       : Int,
   val end         : Int,
   val description : String,
   val tags        : List<String>
   ) : Parcelable