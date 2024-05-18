package ru.aidar.common.data.local.di

import ru.aidar.common.di.FeatureApiHolder
import ru.aidar.common.di.FeatureContainer
import ru.aidar.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class DbHolder @Inject constructor(
    featureContainer: FeatureContainer,
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val deps =
            DaggerDbComponent_DbDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerDbComponent.builder()
            .dbDependencies(deps)
            .build()
    }
}