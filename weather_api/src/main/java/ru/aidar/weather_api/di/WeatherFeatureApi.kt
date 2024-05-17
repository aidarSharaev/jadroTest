package ru.aidar.weather_api.di

import ru.aidar.weather_api.repo.WeatherRepository
import ru.aidar.weather_api.repo.WeatherUseCases

interface WeatherFeatureApi {
    fun provideWeatherRepository(): WeatherRepository
    fun provideWeatherUseCases(): WeatherUseCases
}