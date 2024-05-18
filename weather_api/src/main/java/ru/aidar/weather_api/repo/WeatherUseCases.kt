package ru.aidar.weather_api.repo

import ru.aidar.weather_api.model.ForecastResult

class WeatherUseCases(
    private val repository: WeatherRepository,
) {

    suspend fun getForecast(q: String): ForecastResult {
        return repository.getForecast(q)
    }

    suspend fun getLastLocation() : String? {
        return repository.getLastLocation()
    }
}