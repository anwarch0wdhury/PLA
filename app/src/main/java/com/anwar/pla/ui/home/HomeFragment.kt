package com.anwar.pla.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anwar.pla.MainApp
import com.anwar.pla.R
import com.anwar.pla.common.Resource
import com.anwar.pla.common.Utilities
import com.anwar.pla.di.GlideApp
import com.anwar.pla.model.weather.Current
import com.anwar.pla.ui.weather.DailyForecastAdapter
import com.anwar.pla.ui.weather.HourlyForecastAdapter
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment){

    private lateinit var daily_weather_list : RecyclerView
    private lateinit var hourly_weather_list: RecyclerView
    private lateinit var loadingi_indicator : ProgressBar
    private lateinit var current_weather    : CardView
    private lateinit var current_icon       : ImageView
    private lateinit var errorLabel         : TextView
    private lateinit var current_day        : TextView
    private lateinit var current_time       : TextView
    private lateinit var current_description: TextView
    private lateinit var current_feels      : TextView
    private lateinit var current_temp       : TextView
    private lateinit var current_windspeed  : TextView
    private lateinit var current_humidity   : TextView
    private lateinit var current_address    : TextView
    private lateinit var current_pressure   : TextView
    private lateinit var current_cloud      : TextView
    private lateinit var sunrise            : TextView
    private lateinit var sunset             : TextView

    private val viewModel: HomeViewModel by viewModels()
    private val forecastAdapter = DailyForecastAdapter()
    private val hourlyAdapter   = HourlyForecastAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        when(MainApp.locationActive && MainApp.internetActive){
            true  ->{
                viewModel.location(requireActivity())
                setupObservers()
            }
            false ->{ errorLabel.visibility = VISIBLE }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setCurrentData(current: Current) {

            val iconString = Utilities.iconUrl(icon = current.weather[0].icon)
            current_address.text     = viewModel.address.value.toString()
            current_description.text = MainApp.resourses.getString(R.string.there_are) + current.weather[0].description
            current_temp.text        = current.temp.toInt().toString() + MainApp.resourses.getString(R.string.temperature_unit)
            current_feels.text       = current.feels_like.toInt().toString() + MainApp.resourses.getString(R.string.temperature_unit)
            current_windspeed.text   = Utilities.msToKph(current.wind_speed).toInt().toString() + MainApp.resourses.getString(R.string.speed_unit)
            current_cloud.text       = current.clouds.toString() + MainApp.resourses.getString(R.string.percent)
            current_pressure.text    = current.pressure.toString() + MainApp.resourses.getString(R.string.pressure_unit)
            current_humidity.text    = current.humidity.toString() + MainApp.resourses.getString(R.string.percent)
            sunrise.text             = Utilities.hourToString(current.sunrise.toLong())
            sunset.text              = Utilities.hourToString(current.sunset.toLong())
            GlideApp.with(this)
                .applyDefaultRequestOptions(
                    RequestOptions().placeholder(R.drawable.weather)
                        .error(R.drawable.ic_error)
                )
                .load(iconString)
                .into(current_icon)
    }

    private fun setupObservers() {
        viewModel.current_time.observe(viewLifecycleOwner,{time ->
            current_time.text= time
        })

        viewModel.current_day.observe(viewLifecycleOwner,{day ->
            current_day.text= day
        })

        viewModel.weatherList.observe(viewLifecycleOwner, { forecast ->
            when (forecast.status) {

                Resource.Status.LOADING -> {
                    current_weather.visibility     = GONE
                    current_day.visibility         = GONE
                    current_address.visibility     = GONE
                    daily_weather_list.visibility  = GONE
                    hourly_weather_list.visibility = GONE
                    loadingi_indicator.visibility  = VISIBLE
                    errorLabel.visibility          = GONE
                }
                Resource.Status.SUCCESS -> {
                    current_weather.visibility     = VISIBLE
                    current_day.visibility         = VISIBLE
                    current_address.visibility     = VISIBLE
                    daily_weather_list.visibility  = VISIBLE
                    hourly_weather_list.visibility = VISIBLE
                    loadingi_indicator.visibility  = GONE
                    errorLabel.visibility          = GONE
                    if(forecast.data?.daily != null){
                        forecastAdapter.updateData(forecast.data.daily)
                        hourlyAdapter.updateData(forecast.data.hourly)
                        setCurrentData(forecast.data.current)
                    }
                }
                Resource.Status.ERROR -> {
                    current_weather.visibility     = GONE
                    current_day.visibility         = GONE
                    current_address.visibility     = GONE
                    daily_weather_list.visibility  = GONE
                    hourly_weather_list.visibility = GONE
                    loadingi_indicator.visibility  = GONE
                    errorLabel.visibility          = VISIBLE
                }
            }
        })
    }

    private fun setupUI(view: View) {
        loadingi_indicator  = view.findViewById(R.id.loadingIndicator)
        errorLabel          = view.findViewById(R.id.errorLabel)
        current_weather     = view.findViewById(R.id.cvCurrentWeather)
        daily_weather_list  = view.findViewById(R.id.rvDailyList)
        hourly_weather_list = view.findViewById(R.id.rvHourlyList)
        daily_weather_list.apply {
            this.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
            adapter = forecastAdapter
        }
        hourly_weather_list.apply {
            layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
            adapter = hourlyAdapter
        }
        current_icon        = view.findViewById(R.id.imgvCurrentIcon)
        current_day         = view.findViewById(R.id.tvCurrentDay)
        current_time        = view.findViewById(R.id.tvCurrentTime)
        current_description = view.findViewById(R.id.tvCurrentDescription)
        current_feels       = view.findViewById(R.id.tvCurrentFeels)
        current_pressure    = view.findViewById(R.id.tvCurrentPressure)
        current_cloud       = view.findViewById(R.id.tvCurrentCloud)
        current_temp        = view.findViewById(R.id.tvCurrentTemp)
        current_windspeed   = view.findViewById(R.id.tvCurrentWindSpeed)
        current_humidity    = view.findViewById(R.id.tvCurrentHumidity)
        sunrise             = view.findViewById(R.id.sunrise)
        sunset              = view.findViewById(R.id.sunset)
        current_address     = view.findViewById(R.id.tvCurrentAddress)

    }

}