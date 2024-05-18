package ru.aidar.weather_impl.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.aidar.common.base.BaseViewModel
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.weather_api.model.ApiResult
import ru.aidar.weather_api.repo.WeatherUseCases
import ru.aidar.weather_api.wrap.WeatherState
import ru.aidar.weather_api.wrap.WeatherStateWrapper
import ru.aidar.weather_impl.R
import ru.aidar.weather_impl.WeatherRouter
import ru.aidar.weather_impl.data.utils.forecastPlaceholder
import kotlin.coroutines.CoroutineContext

class WeatherViewModel(
    private val useCases: WeatherUseCases,
    private val wrapper: WeatherStateWrapper,
    private val router: WeatherRouter,
    private val resourceManager: JadroResourceManager,
) : BaseViewModel(), CoroutineScope {

    init {
        launch {
            val cache = useCases.getCachedData()
            cache?.let {
                wrapper.setForecast(it)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + mainJob

    val state = wrapper.flow().stateIn(
        scope = viewModelScope,
        initialValue = WeatherState(forecastLocalModel = forecastPlaceholder),
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun showRationaleDialog(value: Boolean) {
        wrapper.showRationaleDialog(value)
    }

    fun showSettingDialog(value: Boolean) {
        wrapper.showSettingDialog(value)
    }

    fun getForecast() {
        launch {
            val coordDef = async { useCases.getLastLocation() }
            val coord = coordDef.await()
            when {
                (coord == null) -> {
                    wrapper.updateToastText(resourceManager.getString(R.string.check_your_gps_and_internet))
                }

                !wrapper.getFloatingButtonEnabled() -> {}
                else -> {
                    apiCall(coord)
                }
            }
        }
    }

    private suspend fun apiCall(q: String) = coroutineScope {
        wrapper.updateFloatingButtonEnabled(false)
        delay(1_500)
        val forecastDef = async { useCases.getForecast(q = q) }
        val forecast = forecastDef.await()
        when(forecast.result) {
            is ApiResult.Success -> {
                wrapper.setForecast(forecast.model!!)
            }

            else -> {
                wrapper.updateToastText(forecast.message!!)
            }
        }
        wrapper.updateFloatingButtonEnabled(true)
    }

    fun updateHasLocationPermission(value: Boolean) {
        wrapper.updateHasLocationPermission(value)
        if(value)
            getForecast()
    }

    fun resetToast() {
        launch {
            wrapper.updateToastText("")
        }
    }
}