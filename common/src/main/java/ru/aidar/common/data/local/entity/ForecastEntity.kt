package ru.aidar.common.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aidar.common.data.local.model.NextDayLocal

@Entity(tableName = "FORECAST_ENTITY_TABLE")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "lon")
    val lon: Double,
    @ColumnInfo(name = "lastUpdateTime")
    val lastUpdateTime: String,
    @ColumnInfo(name = "currentTempC")
    val currentTempC: Double,
    @ColumnInfo(name = "nextDays")
    val nextDays: List<NextDayLocal>,
)
