package com.anwar.pla.weather.ui

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anwar.pla.MainApp
import com.anwar.pla.R
import com.anwar.pla.common.Utilities
import com.anwar.pla.di.GlideApp
import com.anwar.pla.weather.model.Hourly
import com.bumptech.glide.request.RequestOptions


class HourlyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var hourlyWeatherIcon      = view.findViewById<ImageView>(R.id.imgvHourlyIcon)
    private val hourlyWeatherTime      = view.findViewById<TextView>(R.id.tvHourlyTime)
    private val hourlyWeatherTemp      = view.findViewById<TextView>(R.id.tvHourlyTemp)
    private val hourlyWeatherFeels     = view.findViewById<TextView>(R.id.tvHourlyFeels)
    private val hourlyWeatherHumidity  = view.findViewById<TextView>(R.id.tvHourlyHumidity)
    private val hourlyWeatherCloud     = view.findViewById<TextView>(R.id.tvHourlyCloud)
    private val hourlyWeatherWindSpeed = view.findViewById<TextView>(R.id.tvHourlyWindspeed)

    @SuppressLint("SetTextI18n")
    fun setHourlyData(hourly: Hourly) {
        val iconString   = Utilities.iconUrl(icon = hourly.weather[0].icon)
        hourlyWeatherTime.text      = Utilities.hoursAndDateToString(hourly.dt)
        hourlyWeatherTemp.text      = hourly.temp.toInt().toString() + MainApp.resourses.getString(R.string.temperature_unit)
        hourlyWeatherFeels.text     = hourly.feels_like.toInt().toString() + MainApp.resourses.getString(R.string.temperature_unit)
        hourlyWeatherHumidity.text  = hourly.humidity.toString() + MainApp.resourses.getString(R.string.percent)
        hourlyWeatherCloud.text     = hourly.clouds.toString() + MainApp.resourses.getString(R.string.percent)
        hourlyWeatherWindSpeed.text = hourly.wind_speed.toString() + MainApp.resourses.getString(R.string.speed_unit)
        GlideApp.with(itemView)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.weather).error(R.drawable.ic_error))
            .load(iconString)
            .into(hourlyWeatherIcon)
    }
}