package com.erayucar.myweatherapp.domain.use_case

import com.erayucar.myweatherapp.common.Resource
import com.erayucar.myweatherapp.data.remote.dto.toWeatherForecastInfoMap
import com.erayucar.myweatherapp.domain.model.WeatherForecastInfo
import com.erayucar.myweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(location: String, days: Int): Flow<Resource<WeatherForecastInfo>> = flow {
        try {
            emit(Resource.Loading<WeatherForecastInfo>())
            val weatherForecastInfo = repository.getForecastData(location, days)
            emit(Resource.Success<WeatherForecastInfo>(weatherForecastInfo.toWeatherForecastInfoMap()))

        } catch (e: IOException) {
            emit(Resource.Error<WeatherForecastInfo>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: HttpException){
            emit(Resource.Error<WeatherForecastInfo>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}