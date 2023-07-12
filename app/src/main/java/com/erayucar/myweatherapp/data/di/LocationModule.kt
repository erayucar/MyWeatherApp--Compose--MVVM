package com.erayucar.myweatherapp.data.di

import com.erayucar.myweatherapp.data.location.DefaultLocationTracker
import com.erayucar.myweatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker

}