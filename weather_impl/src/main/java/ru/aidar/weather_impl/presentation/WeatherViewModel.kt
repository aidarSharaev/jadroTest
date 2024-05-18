package ru.aidar.weather_impl.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.aidar.common.base.BaseViewModel
import ru.aidar.weather_api.repo.WeatherUseCases
import ru.aidar.weather_api.wrap.WeatherState
import ru.aidar.weather_api.wrap.WeatherStateWrapper
import ru.aidar.weather_impl.WeatherRouter

class WeatherViewModel(
    private val useCases: WeatherUseCases,
    private val wrapper: WeatherStateWrapper,
    private val router: WeatherRouter,
): BaseViewModel() {

    val state= wrapper.flow().stateIn(
        scope = viewModelScope,
        initialValue = WeatherState(),
        started = SharingStarted.WhileSubscribed(5_000)
    )



}