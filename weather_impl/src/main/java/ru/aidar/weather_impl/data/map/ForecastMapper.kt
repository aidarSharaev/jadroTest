package ru.aidar.weather_impl.data.map

import ru.aidar.weather_api.model.ForecastDay
import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.model.ForecastRemoteModel
import ru.aidar.weather_api.model.IconResult
import ru.aidar.weather_api.model.NextDay
import java.util.Calendar
import javax.inject.Inject

val DayOfTheWeek = mapOf(
    Calendar.SUNDAY to "Sunday",
    Calendar.MONDAY to "Monday",
    Calendar.TUESDAY to "Tuesday",
    Calendar.WEDNESDAY to "Wednesday",
    Calendar.THURSDAY to "Thursday",
    Calendar.FRIDAY to "Friday",
    Calendar.SATURDAY to "Saturday",
)

class ForecastMapper @Inject constructor() {


    fun remoteForecastToLocalForecast(remote: ForecastRemoteModel): ForecastLocalModel {

        val nextDays = forecastDaysToNextDays(remote.current.forecast.forecastDay)

        return with(remote) {
            ForecastLocalModel(
                city = this.location.name,
                lat = this.location.lat,
                lon = this.location.lon,
                lastUpdateTime = this.current.lastUpdated,
                currentTempC = this.current.tempC,
                nextDays = nextDays,
            )
        }
    }

    private fun forecastDaysToNextDays(forecastDay: List<ForecastDay>): List<NextDay> {
        val list = mutableListOf<NextDay>()
        var date = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        for (i in forecastDay) {
            val max = i.day.maxTemp
            val min = i.day.minTemp
            list.add(
                NextDay(
                    date = i.date,
                    maxTemp = max,
                    minTemp = min,
                    icon = iconResult(max, min),
                    weekDay = DayOfTheWeek[date]!!
                )
            )
            if(date == 7) date = 0 else ++date
        }
        return list
    }

    private fun iconResult(max: Double, min: Double): IconResult {
        return if(min > 25 && min + max > 50) IconResult.Warm else IconResult.Cold
    }
}