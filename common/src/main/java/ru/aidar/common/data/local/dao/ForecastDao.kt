package ru.aidar.common.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import ru.aidar.common.data.local.entity.ForecastEntity

@Dao
interface ForecastDao {

    @Query("SELECT * FROM FORECAST_ENTITY_TABLE where id = :id")
    fun getCachedValue(id: Long): ForecastEntity?

    @Query("DELETE FROM FORECAST_ENTITY_TABLE")
    suspend fun deleteAll()
}