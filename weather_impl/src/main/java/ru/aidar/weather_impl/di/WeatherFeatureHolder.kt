package ru.aidar.weather_impl.di

import ru.aidar.common.data.local.di.DbApi
import ru.aidar.common.di.FeatureApiHolder
import ru.aidar.common.di.FeatureContainer
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.weather_impl.WeatherRouter
import javax.inject.Inject

@ApplicationScope
class WeatherFeatureHolder @Inject
constructor(
    featureContainer: FeatureContainer,
    private val router: WeatherRouter,
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val deps =
            DaggerWeatherFeatureComponent_WeatherFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .dbApi(getFeature(DbApi::class.java))
                .build()
        return DaggerWeatherFeatureComponent.builder()
            .withDependencies(deps)
            .router(router)
            .build()
    }
}