package com.erayucar.myweatherapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.myweatherapp.common.Resource
import com.erayucar.myweatherapp.domain.location.LocationTracker
import com.erayucar.myweatherapp.domain.use_case.GetWeatherUseCase
import com.erayucar.myweatherapp.presentation.state.WeatherEvent
import com.erayucar.myweatherapp.presentation.state.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {

     var _weatherState = mutableStateOf(WeatherState())
        private set

    private val currentLocation = MutableStateFlow("istanbul")

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    init {
        loadWeatherInfo()
    }


    fun loadWeatherInfo(SearchCity: String = "") {
        viewModelScope.launch(Dispatchers.Main) {
            _weatherState.value = _weatherState.value.copy(
                isLoading = true
            )
            locationTracker.getCurrentLocation()?.let { location ->
                currentLocation.value = "${location.latitude},${location.longitude}"

            } ?: kotlin.run {
                _weatherState.value = WeatherState(error = "Location not found")

            }


            if (SearchCity != "") {
                currentLocation.value = SearchCity
            }
            getWeatherUseCase(currentLocation.value).onEach {
                when (it) {


                    is Resource.Success -> {
                        _isLoading.value = false
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

    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.GetWeatherForCity -> {
                loadWeatherInfo(event.city)
            }
        }
    }

}

