package ru.aidar.common.monitor

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class JadroLocationManagerImpl(
    private val context: Context,
) : JadroLocationManager {

    private val fusedLocation: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    override suspend fun lastLocationWrapper(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return if(!gpsStatus) {
            Log.d("JadroExc", "!gpsStatus")
            null
        } else {
            Log.d("JadroExc", "gpsStatus")
            getLastKnowLocation()
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun getLastKnowLocation(): Location? =
        suspendCoroutine { continuation ->
            fusedLocation.lastLocation
                .addOnSuccessListener { loc ->
                    continuation.resume(loc)
                }
                .addOnCompleteListener { task ->
                    if(!task.isSuccessful) {
                        Log.d("JadroExc", task.exception.toString())
                        continuation.resume(null)
                    }
                }
        }
}