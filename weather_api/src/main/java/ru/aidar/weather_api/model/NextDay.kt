package ru.aidar.weather_api.model

data class NextDay(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,

    val icon: IconResult,
    val weekDay: String,
)