package ru.aidar.weather_api.wrap

import kotlinx.coroutines.flow.StateFlow

interface WeatherStateWrapper {
    fun flow(): StateFlow<WeatherState>
}