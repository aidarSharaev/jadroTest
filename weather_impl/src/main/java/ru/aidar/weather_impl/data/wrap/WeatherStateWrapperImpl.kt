package ru.aidar.weather_impl.data.wrap

import kotlinx.coroutines.flow.MutableStateFlow
import ru.aidar.weather_api.wrap.WeatherState
import ru.aidar.weather_api.wrap.WeatherStateWrapper

class WeatherStateWrapperImpl(
    private val flow: MutableStateFlow<WeatherState>,
) : WeatherStateWrapper {
}