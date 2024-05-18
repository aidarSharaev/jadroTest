package ru.aidar.weather_api.model

data class ForecastLocalModel(
    val city: String,

    val lat: Double,
    val lon: Double,

    val lastUpdateTime: String,
    val currentTempC: Double,

    val nextDays: List<NextDay>,
)



