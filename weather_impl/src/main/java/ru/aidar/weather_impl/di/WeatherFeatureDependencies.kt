package ru.aidar.weather_impl.di

import ru.aidar.common.core.monitor.JadroNetworkMonitor
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.data.JadroNetworkApiCreator

interface WeatherFeatureDependencies {

    fun networkMonitor(): JadroNetworkMonitor
    fun networkApiCreator(): JadroNetworkApiCreator

    //    fun localManager(): JadroLocalManager
    fun resourceManager(): JadroResourceManager
}