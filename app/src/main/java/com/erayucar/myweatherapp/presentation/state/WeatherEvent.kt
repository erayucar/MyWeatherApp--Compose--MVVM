package com.erayucar.myweatherapp.presentation.state

sealed class WeatherEvent{
     data class GetWeatherForCity(val city: String): WeatherEvent()
}
