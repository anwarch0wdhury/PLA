package com.anwar.pla.common

import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    companion object {


        fun msToKph(speed :Double):Double{
            val kph = speed*3.6
            return kph
        }

        fun day():String{
            val date = Date()
            val formatter = SimpleDateFormat("EEEE, d MMMM")
            return formatter.format(date)
        }

        fun time():String{
            val date = Date()
            val formatter = SimpleDateFormat("hh:mm a")
            return formatter.format(date)
        }

        fun iconUrl(icon: String) : String {
            return "https://openweathermap.org/img/wn/$icon@4x.png";
        }

        fun hourToString(dt: Long) : String{
            val sdf = SimpleDateFormat("hh:mm a")
            val date = Date(dt * 1000)
            return sdf.format(date)
        }

        fun hoursAndDateToString(dt: Long) : String{
            val sdf = SimpleDateFormat("hh:mm a"+"\nd MMMM")
            val date = Date(dt * 1000)
            return sdf.format(date)
        }

        fun dateToString(dt: Long) : String{
            val sdf = SimpleDateFormat("EEEE, d MMMM")
            val date = Date(dt * 1000)
            return sdf.format(date)
        }

    }

}