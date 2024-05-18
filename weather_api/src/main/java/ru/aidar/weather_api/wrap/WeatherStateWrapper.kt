package ru.aidar.weather_api.wrap

import kotlinx.coroutines.flow.StateFlow
import ru.aidar.weather_api.model.ForecastLocalModel

interface WeatherStateWrapper {
    fun flow(): StateFlow<WeatherState>
    fun showRationaleDialog(value: Boolean)
    fun showSettingDialog(value: Boolean)
    fun updateHasLocationPermission(value: Boolean)
    suspend fun setForecast(model: ForecastLocalModel)
    fun getWasApiCalled(): Boolean
    fun getFloatingButtonEnabled(): Boolean
    suspend fun updateFloatingButtonEnabled(value: Boolean)
    suspend fun updateToastText(text: String)
}