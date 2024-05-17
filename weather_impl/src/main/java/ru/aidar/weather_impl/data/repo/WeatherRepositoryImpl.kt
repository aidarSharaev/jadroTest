package ru.aidar.weather_impl.data.repo

import kotlinx.coroutines.CoroutineDispatcher
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.weather_api.remote.WeatherApiService
import ru.aidar.weather_api.repo.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService,
    private val dispatcher: CoroutineDispatcher,
    private val resourceManager: JadroResourceManager,
    private val handler: WeatherQueryHandler
) : WeatherRepository {
}

class WeatherQueryHandler @Inject constructor() {

}