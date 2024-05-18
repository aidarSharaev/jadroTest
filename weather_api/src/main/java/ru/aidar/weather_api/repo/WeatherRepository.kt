package ru.aidar.weather_api.repo

import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.model.ForecastResult

interface WeatherRepository {

    suspend fun getForecast(q: String): ForecastResult
    suspend fun getLastLocation(): String?
    suspend fun cacheLastUpdate(forecastLocalModel: ForecastLocalModel)
    suspend fun getCachedData(): ForecastLocalModel?
}