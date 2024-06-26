package ru.aidar.weather_api.model

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: Day,
)