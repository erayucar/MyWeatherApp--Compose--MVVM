package com.erayucar.myweatherapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.myweatherapp.common.Resource
import com.erayucar.myweatherapp.domain.use_case.GetWeatherForecastUseCase
import com.erayucar.myweatherapp.presentation.state.WeatherForecastState
import com.erayucar.myweatherapp.presentation.state.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val savedStateHandle: SavedStateHandle,

    ):ViewModel(){

    private val _weatherForecastState = mutableStateOf(WeatherForecastState())
    val weatherState: State<WeatherForecastState> = _weatherForecastState

init {

    loadWeatherForecastInfo(savedStateHandle.get("city") ?: "",3)
}
    fun loadWeatherForecastInfo(city :String, days: Int) {
        getWeatherForecastUseCase.invoke(city,days).onEach {
            when (it) {

                is Resource.Loading -> {
                    _weatherForecastState.value = WeatherForecastState(isLoading = true)
                }

                is Resource.Success -> {
                    _weatherForecastState.value = WeatherForecastState(weatherForecast = it.data)
                }

                is Resource.Error -> {
                    _weatherForecastState.value =
                        WeatherForecastState(error = it.message ?: "An unexpected error occurred")
                }

            }
        }.launchIn(viewModelScope)
    }

}