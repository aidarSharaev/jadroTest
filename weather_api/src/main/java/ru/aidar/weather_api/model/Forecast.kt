package ru.aidar.weather_api.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>,
)