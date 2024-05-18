package ru.aidar.weather_impl.data.utils

import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.model.IconResult
import ru.aidar.weather_api.model.NextDay

fun IconResult.getImage(): Int {
    return if(this is IconResult.Cold) {
        ru.aidar.common.R.drawable.ic_cold
    } else {
        ru.aidar.common.R.drawable.ic_sun
    }
}

val forecastPlaceholder =
    ForecastLocalModel(
        city = "Kazan",
        lat = 55.75,
        lon = 49.13,
        lastUpdateTime = "08:45",
        currentTempC = 9.0,
        nextDays = listOf(
            NextDay(
                date = "18.05.2024",
                maxTemp = 12.0,
                minTemp = 8.0,
                icon = IconResult.Cold,
                weekDay = "Monday"
            ),
            NextDay(
                date = "19.05.2024",
                maxTemp = 23.0,
                minTemp = 3.0,
                icon = IconResult.Cold,
                weekDay = "Monday"
            ),
            NextDay(
                date = "20.05.2024",
                maxTemp = 34.0,
                minTemp = 13.0,
                icon = IconResult.Cold,
                weekDay = "Tuesday"
            ),
            NextDay(
                date = "21.05.2024",
                maxTemp = 27.0,
                minTemp = 25.0,
                icon = IconResult.Warm,
                weekDay = "Wednesday"
            ),
            NextDay(
                date = "22.05.2024",
                maxTemp = 10.0,
                minTemp = -1.0,
                icon = IconResult.Cold,
                weekDay = "Friday"
            ),
            NextDay(
                date = "23.05.2024",
                maxTemp = 10.0,
                minTemp = -1.0,
                icon = IconResult.Cold,
                weekDay = "Sunday"
            ),
            NextDay(
                date = "24.05.2024",
                maxTemp = 34.0,
                minTemp = 27.0,
                icon = IconResult.Warm,
                weekDay = "Thursday"
            ),
        )
    )