package com.erayucar.myweatherapp.domain.model

import com.erayucar.myweatherapp.data.remote.dto.Current
import com.erayucar.myweatherapp.data.remote.dto.Hour
import java.time.LocalDateTime

data class WeatherForecastInfo(
    val current: Current,
    val location: String,
    val currentTime : LocalDateTime,
    val dayHour: List<Hour>,

)
fun WeatherForecastInfo.toFormatUrl(icon:String): String {
    val ss = "https:${icon}"
    println(ss)
    return ss
}