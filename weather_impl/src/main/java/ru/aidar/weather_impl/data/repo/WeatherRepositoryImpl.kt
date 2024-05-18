package ru.aidar.weather_impl.data.repo

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.aidar.common.R
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.monitor.JadroLocationManager
import ru.aidar.weather_api.model.ApiResult
import ru.aidar.weather_api.model.ForecastResult
import ru.aidar.weather_api.remote.WeatherApiService
import ru.aidar.weather_api.repo.WeatherRepository
import ru.aidar.weather_impl.data.handler.WeatherQueryHandler
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService,
    private val dispatcher: CoroutineDispatcher,
    private val resourceManager: JadroResourceManager,
    private val handler: WeatherQueryHandler,
    private val monitor: JadroLocationManager
) : WeatherRepository {
    override suspend fun getForecast(q: String): ForecastResult = withContext(dispatcher) {
        try {
            val resp = api.getForecast(q = q)
            handler.handleForecastResponse(resp)
        } catch(e: IOException) {
            Log.d("JadroExc", e.toString())
            ioExceptionResult
        } catch(e: Exception) {
            Log.d("JadroExc", e.toString())
            exceptionResult
        }
    }

    override suspend fun getLastLocation(): String? {
        return try {
            val location = monitor.lastLocationWrapper()
            Log.d("JadroExc", "${location?.latitude ?: "hahhaha"}")
            location?.let { "${it.latitude},${it.longitude}" }
        } catch(e: Exception) {
            Log.d("JadroExc", e.toString())
            null
        }
    }

    private val ioExceptionResult =
        ForecastResult(ApiResult.Fail, null, getErrorMessage(R.string.check_your_connection))
    private val exceptionResult =
        ForecastResult(ApiResult.Error, null, getErrorMessage(R.string.unknown_error))

    private fun getErrorMessage(@StringRes res: Int): String {
        return resourceManager.getString(res)
    }
}





