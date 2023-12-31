package com.erayucar.myweatherapp.domain.use_case

import com.erayucar.myweatherapp.common.Resource
import com.erayucar.myweatherapp.data.remote.dto.toWeatherInfo
import com.erayucar.myweatherapp.domain.model.WeatherInfo
import com.erayucar.myweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(location: String): Flow<Resource<WeatherInfo>> = flow {
        try {
            emit(Resource.Loading())
            val weatherInfo = repository.getWeatherData(location)
            emit(Resource.Success(weatherInfo.toWeatherInfo()))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}