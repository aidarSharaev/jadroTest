package ru.aidar.weather_api.model

data class ForecastLocalModel(
    val city: String,

    val lat: Double,
    val lon: Double,

    val lastUpdateTime: String,
    val currentTempC: Double,

    val nextDays: List<NextDay>,
)

data class NextDay(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,

    val icon: IconResult,
    val weekDay: String,
)

interface IconResult{
    object Cold: IconResult
    object Warm: IconResult
}