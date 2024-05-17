package ru.aidar.jadrotest.app

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.jadrotest.JadroApplication

@Module
class AppModule {
    @ApplicationScope
    @Provides
    fun provideContext(application: JadroApplication): Context {
        return application
    }
}