package com.erayucar.myweatherapp.data.location

import android.app.Application
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationRequest
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

import com.erayucar.myweatherapp.domain.location.LocationTracker

@ExperimentalCoroutinesApi
class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient, private val application: Application
) : LocationTracker {
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )
        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {

            return null
        }
        return suspendCancellableCoroutine { cont ->
            locationClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null)

                .addOnSuccessListener {
                    if (it == null) {
                        Log.d("LocationTracker", "Location is null")
                        cont.resume(null)
                    } else {
                        Log.d("LocationTracker", "Location is not null")
                        cont.resume(it)
                    }
                }
                .addOnFailureListener {
                    cont.resume(null)
                }
                .addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

/*
if (isComplete) {
    if (isSuccessful) {
        cont.resume(result)

    } else {
        cont.resume(null)
    }
    return@suspendCancellableCoroutine
}*/