package ru.aidar.jadrotest

import android.app.Application
import ru.aidar.common.di.CommonApi
import ru.aidar.common.di.FeatureContainer
import ru.aidar.jadrotest.app.AppComponent
import ru.aidar.jadrotest.deps.ComponentDependenciesProvider
import ru.aidar.jadrotest.deps.FeatureHolderManager
import javax.inject.Inject

class JadroApplication : Application(), FeatureContainer {

    @Inject
    lateinit var featureHolderManager: FeatureHolderManager

    @Inject
    lateinit var dependencies: ComponentDependenciesProvider

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.init(this)
        appComponent.inject(this)
    }

    override fun <T> getFeature(key: Class<*>): T {
        return featureHolderManager.getFeature<T>(key)!!
    }

    override fun releaseFeature(key: Class<*>) {
        featureHolderManager.releaseFeature(key)
    }

    override fun commonApi(): CommonApi {
        return appComponent
    }
}