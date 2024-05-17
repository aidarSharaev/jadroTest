package ru.aidar.jadrotest.app

import dagger.Module
import dagger.Provides
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.jadrotest.navigation.JadroNavigator
import ru.aidar.weather_impl.WeatherRouter

@Module
class NavigationModule {

    @ApplicationScope
    @Provides
    fun provideNavigator(): JadroNavigator = JadroNavigator()

    @ApplicationScope
    @Provides
    fun provideLoginRouter(navigator: JadroNavigator): WeatherRouter = navigator

}