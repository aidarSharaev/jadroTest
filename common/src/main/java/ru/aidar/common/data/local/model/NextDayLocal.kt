package ru.aidar.common.data.local.model

data class NextDayLocal(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,

    val icon: Boolean,
    val weekDay: String,
)
