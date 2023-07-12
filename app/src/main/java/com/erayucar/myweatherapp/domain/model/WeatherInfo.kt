package com.erayucar.myweatherapp.domain.model

import java.time.LocalDateTime
import java.util.Date

data class WeatherInfo(
    val condition: String,
    val time: LocalDateTime,
    val city: String,
    val temperature: Double,
    val icon: String,
    val humidity: Int,
    val windSpeed: Double,
    val visibility: Double,
)

fun WeatherInfo.toFormatUrl(icon:String): String {
   val ss = "https:${icon}"
    println(ss)
    return ss
}