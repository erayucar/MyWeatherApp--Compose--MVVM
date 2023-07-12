package com.erayucar.myweatherapp.data.remote.dto

import com.erayucar.myweatherapp.domain.model.WeatherInfo
import okhttp3.internal.http.toHttpDateOrNull
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WeatherDto(
    val current: Current,
    val location: Location
)
fun WeatherDto.toWeatherInfo() = WeatherInfo(
    time = LocalDateTime.parse(current.last_updated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    city = location.name,
    temperature = current.temp_c,
    icon = current.condition.icon,
    humidity = current.humidity,
    windSpeed = current.wind_kph,
    visibility = current.vis_km,
    condition = current.condition.text)