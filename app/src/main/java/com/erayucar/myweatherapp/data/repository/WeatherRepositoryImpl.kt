package com.erayucar.myweatherapp.data.repository

import com.erayucar.myweatherapp.data.remote.WeatherAPI
import com.erayucar.myweatherapp.data.remote.dto.WeatherForecastDto
import com.erayucar.myweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI
) : WeatherRepository {
    override suspend fun getWeatherData(location: String) = api.getWeatherData(query = location)

    override suspend fun getForecastData(location: String, days: Int): WeatherForecastDto {
        return api.getForecastData(location = location, days = days)
    }

}