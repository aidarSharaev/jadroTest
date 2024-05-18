package ru.aidar.common.monitor

import android.location.Location

interface JadroLocationManager {
    suspend fun getLastKnowLocation(): Location?
    suspend fun lastLocationWrapper(): Location?
}