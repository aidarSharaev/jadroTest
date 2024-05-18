package ru.aidar.common.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.aidar.common.core.config.JadroAppProperties
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.core.resources.JadroResourceManagerImpl
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.common.monitor.JadroLocationManager
import ru.aidar.common.monitor.JadroLocationManagerImpl

@Module
class CommonModule {

    @Provides
    @ApplicationScope
    fun provideAppProperties(context: Context): JadroAppProperties {
        return JadroAppProperties(context = context)
    }

    @Provides
    @ApplicationScope
    fun provideResourceManager(context: Context): JadroResourceManager {
        return JadroResourceManagerImpl(context = context)
    }

    @Provides
    @ApplicationScope
    fun provideLocationManager(context: Context): JadroLocationManager {
        return JadroLocationManagerImpl(context = context)
    }
}