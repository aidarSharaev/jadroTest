package ru.aidar.common.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.aidar.common.core.config.JadroAppProperties
import ru.aidar.common.core.config.JadroNetworkProperties
import ru.aidar.common.core.monitor.JadroNetworkMonitor
import ru.aidar.common.core.monitor.JadroNetworkMonitorImpl
import ru.aidar.common.data.remote.JadroNetworkApiCreator
import ru.aidar.common.di.scope.ApplicationScope
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideNetworkProperties(appProperties: JadroAppProperties): JadroNetworkProperties {
        return appProperties.networkProperties()
    }

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkProperties: JadroNetworkProperties,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor = httpLoggingInterceptor)
            .connectTimeout(networkProperties.connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(networkProperties.writeTimeout, TimeUnit.SECONDS)
            .readTimeout(networkProperties.readTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideApiCreator(
        okHttpClient: OkHttpClient,
        appProperties: JadroAppProperties,
    ): JadroNetworkApiCreator {
        return JadroNetworkApiCreator(
            okHttpClient = okHttpClient,
            weatherUrl = appProperties.getWeatherUrl(),
        )
    }

    @Provides
    @ApplicationScope
    fun provideNetworkMonitor(context: Context): JadroNetworkMonitor {
        return JadroNetworkMonitorImpl(context = context)
    }
}