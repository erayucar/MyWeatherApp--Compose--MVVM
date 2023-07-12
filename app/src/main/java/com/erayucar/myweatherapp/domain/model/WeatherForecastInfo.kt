package com.erayucar.myweatherapp.domain.model

import com.erayucar.myweatherapp.data.remote.dto.Day
import com.erayucar.myweatherapp.data.remote.dto.Hour
import java.time.LocalDateTime

data class WeatherForecastInfo(
    val condition: String,
    val conditionImageUrl: String,
    val location: String,
    val currentTime : LocalDateTime,
    val dayHour: List<Hour>,
    val temperature: Double,

)
fun WeatherForecastInfo.toFormatUrl(icon:String): String {
    val ss = "https:${icon}"
    println(ss)
    return ss
}