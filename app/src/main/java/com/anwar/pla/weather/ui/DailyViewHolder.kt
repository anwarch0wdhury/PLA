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
import com.anwar.pla.weather.model.Forecast
import com.bumptech.glide.request.RequestOptions

class DailyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var dailyWeatherIcon    = view.findViewById<ImageView>(R.id.imgvDailyIcon)
    private val dailyWeatherTime    = view.findViewById<TextView>(R.id.tvDailyTime)
    private val dailyWeatherMinTemp = view.findViewById<TextView>(R.id.tvMinTemp)
    private val dailyWeatherMaxTemp = view.findViewById<TextView>(R.id.tvMaxTemp)

    @SuppressLint("SetTextI18n")
    fun setDailyWeatherData(dailyForecast: Forecast) {
        val iconString = Utilities.iconUrl(icon = dailyForecast.weather[0].icon)
        dailyWeatherTime.text    = Utilities.dateToString(dailyForecast.dt)
        dailyWeatherMinTemp.text = dailyForecast.temp.min.toInt().toString() + MainApp.resourses.getString(R.string.temperature_unit)
        dailyWeatherMaxTemp.text = dailyForecast.temp.max.toInt().toString() + MainApp.resourses.getString(R.string.temperature_unit)
        GlideApp.with(itemView)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.weather).error(R.drawable.ic_error))
            .load(iconString)
            .into(dailyWeatherIcon)
    }
}