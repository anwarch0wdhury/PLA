package com.anwar.pla.weather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anwar.pla.R
import com.anwar.pla.common.DiffCallback
import com.anwar.pla.weather.model.Hourly

class HourlyForecastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = arrayListOf<Hourly>()

    fun updateData(update : List<Hourly>) {
        val diffCallback = DiffCallback(items, update)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = update as ArrayList<Hourly>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hourly, parent, false)
        return HourlyViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HourlyViewHolder).setHourlyData(hourly = items[position])
    }

    override fun getItemCount() = items.size

}