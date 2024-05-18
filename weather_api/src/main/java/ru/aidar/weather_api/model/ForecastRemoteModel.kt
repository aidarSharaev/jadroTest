package ru.aidar.weather_api.model

import com.google.gson.annotations.SerializedName
import ru.aidar.weather_api.remote.Current
import ru.aidar.weather_api.remote.Location

data class ForecastRemoteModel(
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast,
)









