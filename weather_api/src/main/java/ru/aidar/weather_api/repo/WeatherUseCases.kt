package ru.aidar.weather_api.repo

import ru.aidar.weather_api.model.ApiResult
import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.model.ForecastResult

class WeatherUseCases(
    private val repository: WeatherRepository,
) {

    suspend fun getForecast(q: String): ForecastResult {
        val forecast = repository.getForecast(q)
        if(forecast.result is ApiResult.Success) {
            repository.cacheLastUpdate(forecast.model!!)
        }
        return forecast
    }

    suspend fun getLastLocation(): String? {
        return repository.getLastLocation()
    }

    suspend fun getCachedData(): ForecastLocalModel? {
        return repository.getCachedData()
    }
}