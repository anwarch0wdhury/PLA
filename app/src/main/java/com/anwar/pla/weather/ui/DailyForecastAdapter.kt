package com.anwar.pla.weather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anwar.pla.R
import com.anwar.pla.common.DiffCallback
import com.anwar.pla.weather.model.Forecast

class DailyForecastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = arrayListOf<Forecast>()

    fun updateData(update: List<Forecast>) {
        val diffCallback = DiffCallback(items, update)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = update as ArrayList<Forecast>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return DailyViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as DailyViewHolder).setDailyWeatherData(dailyForecast = items[position])

    }

    override fun getItemCount() = items.size
}