package com.erayucar.myweatherapp.domain.repository

import com.erayucar.myweatherapp.data.remote.dto.WeatherDto
import com.erayucar.myweatherapp.data.remote.dto.WeatherForecastDto

interface WeatherRepository {
    suspend fun getWeatherData(location: String): WeatherDto

    suspend fun getForecastData(location: String, days: Int): WeatherForecastDto
}