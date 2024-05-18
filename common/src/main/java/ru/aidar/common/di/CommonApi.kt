package ru.aidar.common.di

import android.content.Context
import ru.aidar.common.core.config.JadroAppProperties
import ru.aidar.common.core.monitor.JadroNetworkMonitor
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.data.remote.JadroNetworkApiCreator
import ru.aidar.common.monitor.JadroLocationManager

interface CommonApi {
    fun applicationContext(): Context
    fun provideNetworkApiCreator(): JadroNetworkApiCreator
    fun provideAppProperties(): JadroAppProperties
    fun provideResourceManager(): JadroResourceManager
    fun provideLocationManager(): JadroLocationManager
    fun provideNetworkMonitor(): JadroNetworkMonitor
}