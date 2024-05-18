package ru.aidar.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.aidar.common.data.local.Constants.JADRO_DATABASE
import ru.aidar.common.data.local.dao.ForecastDao
import ru.aidar.common.data.local.entity.Converters
import ru.aidar.common.data.local.entity.ForecastEntity

@Database(
    entities = [ForecastEntity::class],
    version = 1,
)
@TypeConverters(value = [Converters::class])
abstract class JadroDatabase : RoomDatabase() {
    companion object {
        fun get(context: Context): JadroDatabase =
            Room
                .databaseBuilder(
                    context.applicationContext,
                    JadroDatabase::class.java,
                    JADRO_DATABASE,
                )
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun forecastDao(): ForecastDao
}