package com.erayucar.myweatherapp.data.di

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.erayucar.myweatherapp.common.Constants
import com.erayucar.myweatherapp.data.remote.WeatherAPI
import com.erayucar.myweatherapp.data.repository.WeatherRepositoryImpl
import com.erayucar.myweatherapp.domain.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideWeatherAPI(application: Application): WeatherAPI {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherAPI): WeatherRepository {
        return WeatherRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }

}