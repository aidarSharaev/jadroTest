package ru.aidar.common.core.config

import android.content.Context
import ru.aidar.common.R
import java.util.Properties

@Suppress("UNCHECKED_CAST")
class JadroAppProperties(context: Context) {
    private val properties: Map<String, String> = initProperties(context)

    private fun initProperties(context: Context): Map<String, String> {
        return context.resources.openRawResource(R.raw.config).use {
            val properties = Properties()
            properties.load(it)
            properties as Map<String, String>
        }
    }

    fun networkProperties(): JadroNetworkProperties {
        val connectTimeout = properties["http.timeout.connect"]?.toLong() ?: 0
        val readTimeout = properties["http.timeout.read"]?.toLong() ?: 0
        val writeTimeout = properties["http.timeout.write"]?.toLong() ?: 0
        return JadroNetworkProperties(connectTimeout, readTimeout, writeTimeout)
    }

    fun getWeatherUrl(): String = properties["weather_url"] ?: throw RuntimeException()
}
