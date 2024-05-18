package ru.aidar.weather_impl.di

import dagger.BindsInstance
import dagger.Component
import ru.aidar.common.data.local.di.DbApi
import ru.aidar.common.di.CommonApi
import ru.aidar.common.di.scope.weather.WeatherFeatureScope
import ru.aidar.weather_api.di.WeatherFeatureApi
import ru.aidar.weather_impl.WeatherRouter
import ru.aidar.weather_impl.presentation.di.WeatherComponent


@WeatherFeatureScope
@Component(
    dependencies = [WeatherFeatureDependencies::class],
    modules = [WeatherFeatureModule::class]
)
interface WeatherFeatureComponent : WeatherFeatureApi {

    fun weatherLoginComponentFactory(): WeatherComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun router(router: WeatherRouter): Builder

        fun withDependencies(deps: WeatherFeatureDependencies): Builder

        fun build(): WeatherFeatureComponent
    }

    @Component(dependencies = [CommonApi::class, DbApi::class])
    interface WeatherFeatureDependenciesComponent : WeatherFeatureDependencies
}