package com.erayucar.myweatherapp.data.remote.dto

import com.erayucar.myweatherapp.domain.model.WeatherForecastInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WeatherForecastDto(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)

fun WeatherForecastDto.toWeatherForecastInfoMap() = WeatherForecastInfo(
    location = location.name,
    dayHour = forecast.forecastday[0].hour,
    currentTime = LocalDateTime.parse(current.last_updated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    condition = current.condition.text,
    temperature = current.temp_c,
    conditionImageUrl = current.condition.icon

)