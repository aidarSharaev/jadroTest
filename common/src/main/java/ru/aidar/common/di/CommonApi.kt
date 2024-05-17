package ru.aidar.common.di

import android.content.Context
import ru.aidar.common.core.config.JadroAppProperties
import ru.aidar.common.core.monitor.JadroNetworkMonitor
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.data.JadroNetworkApiCreator

interface CommonApi {
    fun applicationContext(): Context

    fun provideNetworkApiCreator(): JadroNetworkApiCreator

    fun provideAppProperties(): JadroAppProperties

    fun provideResourceManager(): JadroResourceManager

//    fun provideLocalManager(): JadroLocalManager // todo mb delete

    fun provideNetworkMonitor(): JadroNetworkMonitor
}