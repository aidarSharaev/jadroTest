package com.example.horizonGuard.di.deps

import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.aidar.common.di.FeatureApiHolder
import ru.aidar.common.di.FeatureContainer
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.jadrotest.JadroApplication
import ru.aidar.weather_impl.di.WeatherFeatureHolder
import ru.aidar.weather_api.di.WeatherFeatureApi

@Module
interface ComponentHolderModule {
    @ApplicationScope
    @Binds
    fun provideFeatureContainer(application: JadroApplication): FeatureContainer

    @ApplicationScope
    @Binds
    @ClassKey(WeatherFeatureApi::class)
    @IntoMap
    fun provideAuthFeatureHolder(weatherFeatureHolder: WeatherFeatureHolder): FeatureApiHolder
}
