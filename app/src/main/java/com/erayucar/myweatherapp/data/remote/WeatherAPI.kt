package com.erayucar.myweatherapp.data.remote

import com.erayucar.myweatherapp.common.Constants
import com.erayucar.myweatherapp.data.remote.dto.WeatherDto
import com.erayucar.myweatherapp.data.remote.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/v1/current.json?")
    suspend fun getWeatherData(
        @Query("key") key : String = Constants.API_KEY,
        @Query("q") query: String,
    ): WeatherDto

    @GET("/v1/forecast.json?")
    suspend fun getForecastData(
        @Query("key") key : String = Constants.API_KEY,
        @Query("q") location : String,
        @Query("days") days : Int ,
    ): WeatherForecastDto
}