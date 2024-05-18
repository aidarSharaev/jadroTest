package ru.aidar.weather_impl.data.map

import android.util.Log
import ru.aidar.weather_api.model.ForecastDay
import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.model.ForecastRemoteModel
import ru.aidar.weather_api.model.IconResult
import ru.aidar.weather_api.model.NextDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random

private val dayOfTheWeek = mapOf(
    Calendar.SUNDAY to "Sunday",
    Calendar.MONDAY to "Monday",
    Calendar.TUESDAY to "Tuesday",
    Calendar.WEDNESDAY to "Wednesday",
    Calendar.THURSDAY to "Thursday",
    Calendar.FRIDAY to "Friday",
    Calendar.SATURDAY to "Saturday",
)


class ForecastMapper @Inject constructor() {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    private val calendar = Calendar.getInstance()

    fun remoteForecastToLocalForecast(remote: ForecastRemoteModel): ForecastLocalModel {

        Log.d("asdasd", remote.toString())

        val nextDays = forecastDaysToNextDays(remote.forecast.forecastDay)

        return with(remote) {
            ForecastLocalModel(
                city = this.location.name,
                lat = this.location.lat,
                lon = this.location.lon,
                lastUpdateTime = this.current.lastUpdated.split(" ").last(),
                currentTempC = this.current.tempC,
                nextDays = nextDays,
            )
        }
    }

    private fun forecastDaysToNextDays(forecastDay: List<ForecastDay>): List<NextDay> {
        val list = mutableListOf<NextDay>()
        var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        for (i in forecastDay) {
            val max = i.day.maxTemp
            val min = i.day.minTemp
            list.add(
                NextDay(
                    date = i.date.split("-").reversed().joinToString("."),
                    maxTemp = max,
                    minTemp = min,
                    icon = iconResult(max, min),
                    weekDay = dayOfTheWeek[dayOfWeek]!!
                )
            )
            if(dayOfWeek == 7) dayOfWeek = 1 else ++dayOfWeek
        }

        calendar.time =
            dateFormat.parse(list.last().date)!!

        for (i in 0..4) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val max = String.format("%.1f", Random.nextDouble(-5.0, 25.0)).toDouble()
            val min = String.format("%.1f", Random.nextDouble(-10.0, max)).toDouble()
            list.add(
                NextDay(
                    date = dateFormat.format(calendar.time),
                    maxTemp = max,
                    minTemp = min,
                    icon = iconResult(max, min),
                    weekDay = dayOfTheWeek[dayOfWeek]!!
                )
            )
            if(dayOfWeek == 7) dayOfWeek = 1 else ++dayOfWeek
        }

        return list
    }

    private fun iconResult(max: Double, min: Double): IconResult {
        return if(min > 15 && min + max > 40) IconResult.Warm else IconResult.Cold
    }
}