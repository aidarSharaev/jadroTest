package ru.aidar.weather_api.remote

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
)