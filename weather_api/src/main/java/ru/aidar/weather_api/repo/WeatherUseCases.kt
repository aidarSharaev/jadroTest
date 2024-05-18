package ru.aidar.weather_api.repo

class WeatherUseCases(
    private val repository: WeatherRepository
) {

    suspend fun getForecast(q: String) {
        repository.getForecast(q)
    }
}