package com.erayucar.myweatherapp.presentation

sealed class Screen(val route: String){
    object weatherScreen: Screen("weather_screen")
    object weatherDeatilScreen: Screen("weather_detail_screen")

}
