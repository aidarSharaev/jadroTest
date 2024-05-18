package ru.aidar.weather_api.model

data class ForecastResult(
    val result: ApiResult,
    val model: ForecastLocalModel?,
    val message: String?,
)