package com.erayucar.myweatherapp.presentation.state

import com.erayucar.myweatherapp.domain.model.WeatherInfo

data class WeatherState(
    val isLoading: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val error: String = "",
    val search: String = ""
) {
}