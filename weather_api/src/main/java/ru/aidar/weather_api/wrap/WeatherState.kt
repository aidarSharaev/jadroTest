package ru.aidar.weather_api.wrap

import ru.aidar.weather_api.model.ForecastLocalModel

data class WeatherState(
    val hasLocationPermission: Boolean = false,
    val wasApiCalled: Boolean = false,
    val showSettingAlert: Boolean = false,
    val showRationaleAlert: Boolean = false,
    val forecastLocalModel: ForecastLocalModel,

    val floatingButtonEnabled: Boolean = true,
    val toastText: String = "",
)
