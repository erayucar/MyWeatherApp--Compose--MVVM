package com.erayucar.myweatherapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.myweatherapp.common.Resource
import com.erayucar.myweatherapp.domain.location.LocationTracker
import com.erayucar.myweatherapp.domain.use_case.GetWeatherUseCase
import com.erayucar.myweatherapp.presentation.state.WeatherEvent
import com.erayucar.myweatherapp.presentation.state.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {

   private val _weatherState = mutableStateOf(WeatherState())
    val weatherState: State<WeatherState> = _weatherState
    var currentLocation: String = ""

    init {
        loadWeatherInfo(weatherState.value.search)

    }



    fun loadWeatherInfo(SearchCity: String = ""){
        println(weatherState.value.weatherInfo?.temperature)
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                currentLocation = "${location.latitude},${location.longitude}"
            }
            if (SearchCity != ""){
                currentLocation = SearchCity
            }
            getWeatherUseCase(currentLocation).onEach {
                when (it) {



                    is Resource.Success -> {
                        _weatherState.value = WeatherState(weatherInfo = it.data)

                    }

                    is Resource.Error -> {
                        _weatherState.value =
                            WeatherState(error = it.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _weatherState.value = WeatherState(isLoading = true)
                    }

                }
            }.launchIn(viewModelScope)
        }
    }
    fun onEvent(event: WeatherEvent,) {
        when (event) {
            is WeatherEvent.GetWeatherForCity -> {
                loadWeatherInfo(event.city)
            }
        }
    }
}

