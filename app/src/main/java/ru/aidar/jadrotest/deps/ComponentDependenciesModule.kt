package ru.aidar.jadrotest.deps

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.aidar.jadrotest.app.AppComponent
import ru.aidar.jadrotest.main.MainDependencies

@Module
interface ComponentDependenciesModule {
    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainDependencies::class)
    fun provideMainDependencies(component: AppComponent): ComponentDependencies
}
