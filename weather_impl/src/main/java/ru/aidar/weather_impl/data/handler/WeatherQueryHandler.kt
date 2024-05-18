package ru.aidar.weather_impl.data.handler

import retrofit2.Response
import ru.aidar.common.R
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.weather_api.model.ApiResult
import ru.aidar.weather_api.model.ForecastRemoteModel
import ru.aidar.weather_api.model.ForecastResult
import ru.aidar.weather_impl.data.map.ForecastMapper
import javax.inject.Inject

class WeatherQueryHandler @Inject constructor(
    private val mapper: ForecastMapper,
    private val resourceManager: JadroResourceManager,
) {
    fun handleForecastResponse(resp: Response<ForecastRemoteModel?>): ForecastResult {
        return if(resp.isSuccessful && resp.body() != null) {
            ForecastResult(
                result = ApiResult.Success,
                model = mapper.remoteForecastToLocalForecast(resp.body()!!),
                message = null
            )
        } else {
            ForecastResult(
                result = ApiResult.Fail,
                null,
                resourceManager.getString(R.string.request_failed)
            )
        }
    }
}