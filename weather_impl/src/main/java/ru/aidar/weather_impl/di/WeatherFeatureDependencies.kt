package ru.aidar.weather_impl.di

import ru.aidar.common.core.monitor.JadroNetworkMonitor
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.data.remote.JadroNetworkApiCreator
import ru.aidar.common.monitor.JadroLocationManager

interface WeatherFeatureDependencies {

    fun networkMonitor(): JadroNetworkMonitor
    fun networkApiCreator(): JadroNetworkApiCreator
    fun locationManager(): JadroLocationManager
    fun resourceManager(): JadroResourceManager
}