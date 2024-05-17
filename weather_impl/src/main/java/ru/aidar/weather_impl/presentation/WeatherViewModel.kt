package ru.aidar.weather_impl.presentation

import ru.aidar.common.base.BaseViewModel
import ru.aidar.weather_api.repo.WeatherUseCases
import ru.aidar.weather_api.wrap.WeatherStateWrapper
import ru.aidar.weather_impl.WeatherRouter

class WeatherViewModel(
    private val useCases: WeatherUseCases,
    private val wrapper: WeatherStateWrapper,
    private val router: WeatherRouter,
): BaseViewModel() {


}