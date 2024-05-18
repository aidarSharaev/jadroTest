package ru.aidar.jadrotest.app

import dagger.Module
import dagger.Provides
import ru.aidar.common.di.FeatureApiHolder
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.jadrotest.deps.FeatureHolderManager

@Module
class FeatureManagerModule {

    @ApplicationScope
    @Provides
    fun provideFeatureHolderManager(featureApiHolderMap: @JvmSuppressWildcards Map<Class<*>, FeatureApiHolder>): FeatureHolderManager {
        return FeatureHolderManager(featureApiHolderMap)
    }
}