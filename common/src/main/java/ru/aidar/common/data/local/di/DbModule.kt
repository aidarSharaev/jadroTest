package ru.aidar.common.data.local.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.aidar.common.data.local.JadroDatabase
import ru.aidar.common.data.local.dao.ForecastDao
import ru.aidar.common.di.scope.ApplicationScope

@Module
class DbModule {
    @Provides
    @ApplicationScope
    fun provideAppDatabase(context: Context): JadroDatabase {
        return JadroDatabase.get(context)
    }

    @Provides
    @ApplicationScope
    fun provideForecastDao(appDatabase: JadroDatabase): ForecastDao {
        return appDatabase.forecastDao()
    }
}