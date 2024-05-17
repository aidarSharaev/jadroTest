package ru.aidar.common.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JadroNetworkApiCreator(
    private val okHttpClient: OkHttpClient,
    private val weatherUrl: String,
) {

    fun <T> getWeatherService(service: Class<T>): T {
        return create(url = weatherUrl, service = service)
    }

    private fun <T> create(
        url: String,
        service: Class<T>,
    ): T {
        val retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setLenient()
                            .create(),
                    ),
                )
                .build()
        return retrofit.create(service)
    }
}
