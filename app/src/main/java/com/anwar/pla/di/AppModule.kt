package com.anwar.pla.di

import com.anwar.pla.common.Constants
import com.anwar.pla.namaj.datasource.NamajRemoteDataSource
import com.anwar.pla.namaj.remote.NamajService
import com.anwar.pla.weather.datasource.ForecastRemoteDataSource
import com.anwar.pla.weather.remote.ForecastService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("Weather")
    fun provideWeatherRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.WEATHER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideForecastService(@Named("Weather") retrofit: Retrofit): ForecastService =
        retrofit.create(ForecastService::class.java)

    @Singleton
    @Provides
    fun provideForecastRemoteDataSource(forecastService: ForecastService): ForecastRemoteDataSource =
        ForecastRemoteDataSource(forecastService)

    @Singleton
    @Provides
    @Named("Namaj")
    fun provideNamajRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.NAMAJ_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideNamajService(@Named("Namaj") retrofit: Retrofit): NamajService =
        retrofit.create(NamajService::class.java)

    @Singleton
    @Provides
    fun provideNamajRemoteDataSource(namajService: NamajService): NamajRemoteDataSource =
        NamajRemoteDataSource(namajService)

}