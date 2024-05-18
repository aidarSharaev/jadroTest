package ru.aidar.weather_impl.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import ru.aidar.common.data.remote.JadroNetworkApiCreator
import ru.aidar.common.di.scope.weather.WeatherFeatureScope
import ru.aidar.weather_api.remote.WeatherApiService
import ru.aidar.weather_api.repo.WeatherRepository
import ru.aidar.weather_api.repo.WeatherUseCases
import ru.aidar.weather_api.wrap.WeatherState
import ru.aidar.weather_api.wrap.WeatherStateWrapper
import ru.aidar.weather_impl.data.repo.WeatherRepositoryImpl
import ru.aidar.weather_impl.data.utils.forecastPlaceholder
import ru.aidar.weather_impl.data.wrap.WeatherStateWrapperImpl

@Module
class WeatherFeatureModule {
    @Provides
    @WeatherFeatureScope
    fun providesIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @WeatherFeatureScope
    fun provideWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository {
        return repository
    }

    @Provides
    @WeatherFeatureScope
    fun provideWeatherUseCases(repository: WeatherRepository): WeatherUseCases {
        return WeatherUseCases(repository = repository)
    }

    @Provides
    fun provideWeatherState(): MutableStateFlow<WeatherState> {
        return MutableStateFlow(WeatherState(forecastLocalModel = forecastPlaceholder))
    }

    @Provides
    @WeatherFeatureScope
    fun provideWeatherStateWrapper(
        flow: MutableStateFlow<WeatherState>,
    ): WeatherStateWrapper {
        return WeatherStateWrapperImpl(flow)
    }

    @Provides
    @WeatherFeatureScope
    fun provideLoginService(networkApiCreator: JadroNetworkApiCreator): WeatherApiService {
        return networkApiCreator.getWeatherService(WeatherApiService::class.java)
    }
}