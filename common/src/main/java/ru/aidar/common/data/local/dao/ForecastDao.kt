package ru.aidar.common.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.aidar.common.data.local.Constants.FORECAST_TABLE
import ru.aidar.common.data.local.entity.ForecastEntity

@Dao
interface ForecastDao {

    @Query("SELECT * FROM $FORECAST_TABLE where id = :id")
    fun getCachedValue(id: Long): ForecastEntity?

    @Query("DELETE FROM $FORECAST_TABLE")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(forecastEntity: ForecastEntity)
}