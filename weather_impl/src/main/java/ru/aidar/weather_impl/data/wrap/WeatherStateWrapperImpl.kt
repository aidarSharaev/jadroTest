package ru.aidar.weather_impl.data.wrap

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.wrap.WeatherState
import ru.aidar.weather_api.wrap.WeatherStateWrapper

class WeatherStateWrapperImpl(
    private val flow: MutableStateFlow<WeatherState>,
) : WeatherStateWrapper {

    companion object {
        private val mainDispatch = Dispatchers.Main
    }

    override fun flow(): StateFlow<WeatherState> {
        return flow
    }

    override fun showRationaleDialog(value: Boolean) {
        flow.update { it.copy(showRationaleAlert = value) }
    }

    override fun showSettingDialog(value: Boolean) {
        flow.update { it.copy(showSettingAlert = value) }
    }

    override suspend fun setForecast(model: ForecastLocalModel) = withContext(mainDispatch) {
        flow.update { it.copy(forecastLocalModel = model) }
    }

    override fun updateHasLocationPermission(value: Boolean) {
        flow.update { it.copy(hasLocationPermission = value) }
    }

    override fun getWasApiCalled(): Boolean {
        return flow.value.wasApiCalled
    }

    override fun getFloatingButtonEnabled(): Boolean {
        return flow.value.floatingButtonEnabled
    }

    override suspend fun updateFloatingButtonEnabled(value: Boolean) = withContext(mainDispatch) {
        flow.update { it.copy(floatingButtonEnabled = value) }
    }

    override suspend fun updateToastText(text: String) {
        flow.update { it.copy(toastText = text) }
    }
}