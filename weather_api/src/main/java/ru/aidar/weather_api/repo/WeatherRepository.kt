package ru.aidar.weather_api.repo

import ru.aidar.weather_api.model.ForecastResult

interface WeatherRepository {

    suspend fun getForecast(q: String): ForecastResult
}