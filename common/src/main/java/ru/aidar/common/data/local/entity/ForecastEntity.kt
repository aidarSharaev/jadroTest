package ru.aidar.common.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.aidar.common.data.local.Constants.FORECAST_TABLE
import ru.aidar.common.data.local.model.NextDayLocal

@Entity(tableName = FORECAST_TABLE)
data class ForecastEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
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


class Converters {

    @TypeConverter
    fun mapListToString(value: List<NextDayLocal>): String {
        val gson = Gson()
        val type = object : TypeToken<List<NextDayLocal>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<NextDayLocal> {
        val gson = Gson()
        val type = object : TypeToken<List<NextDayLocal>>() {}.type
        return gson.fromJson(value, type)
    }
}
