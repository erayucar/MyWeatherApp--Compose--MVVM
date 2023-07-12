package com.erayucar.myweatherapp.presentation.state

import com.erayucar.myweatherapp.domain.model.WeatherForecastInfo

class WeatherForecastState(
    val isLoading: Boolean = false,
    val weatherForecast: WeatherForecastInfo? = null,
    val error: String = "",
    val search: String = "Istanbul"
) {
}