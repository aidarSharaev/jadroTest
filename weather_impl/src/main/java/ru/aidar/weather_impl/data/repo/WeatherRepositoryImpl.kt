package ru.aidar.weather_impl.data.repo

import android.util.Log
import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.aidar.common.R
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.data.local.JadroDatabase
import ru.aidar.common.monitor.JadroLocationManager
import ru.aidar.weather_api.model.ApiResult
import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.model.ForecastResult
import ru.aidar.weather_api.remote.WeatherApiService
import ru.aidar.weather_api.repo.WeatherRepository
import ru.aidar.weather_impl.data.handler.WeatherQueryHandler
import ru.aidar.weather_impl.data.utils.toEntity
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService,
    private val dispatcher: CoroutineDispatcher,
    private val resourceManager: JadroResourceManager,
    private val handler: WeatherQueryHandler,
    private val monitor: JadroLocationManager,
    private val database: JadroDatabase,
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

    override suspend fun getLastLocation(): String? = withContext(dispatcher) {
        try {
            val location = monitor.lastLocationWrapper()
            location?.let { "${it.latitude},${it.longitude}" }
        } catch(e: Exception) {
            Log.d("JadroExc", e.toString())
            null
        }
    }

    override suspend fun getCachedData(): ForecastLocalModel? = withContext(dispatcher) {
        val data = database.forecastDao().getCachedValue(0)
        data?.toEntity()
    }

    override suspend fun cacheLastUpdate(forecastLocalModel: ForecastLocalModel) {
        database.forecastDao().deleteAll()
        database.forecastDao().insertData(forecastLocalModel.toEntity())
    }

    private val ioExceptionResult =
        ForecastResult(ApiResult.Fail, null, getErrorMessage(R.string.check_your_connection))
    private val exceptionResult =
        ForecastResult(ApiResult.Error, null, getErrorMessage(R.string.unknown_error))

    private fun getErrorMessage(@StringRes res: Int): String {
        return resourceManager.getString(res)
    }
}





