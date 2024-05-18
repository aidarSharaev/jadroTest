package ru.aidar.weather_api.model

import com.google.gson.annotations.SerializedName

data class ForecastRemoteModel(
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current,

)

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
)

data class Current(
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("forecast")
    val forecast: Forecast,
)

data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>,
)

data class ForecastDay(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: Day,
)

data class Day(
    @SerializedName("maxtemp_c")
    val maxTemp: Double,
    @SerializedName("mintemp_c")
    val minTemp: Double,
)