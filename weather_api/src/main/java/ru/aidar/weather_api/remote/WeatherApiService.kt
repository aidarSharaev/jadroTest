package ru.aidar.weather_api.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.aidar.weather_api.model.ForecastRemoteModel

private const val WeatherApiKey = "d04470e6a8764b72847172105230809"
private const val FORECAST_DAYS_COUNT = 10

interface WeatherApiService {

    @GET("/v1/forecast.json")
    suspend fun getForecast(
        @Query("key") key: String = WeatherApiKey,
        @Query("days") days: Int = FORECAST_DAYS_COUNT,
        @Query("q") q: String,
    ): Response<ForecastRemoteModel?>
}