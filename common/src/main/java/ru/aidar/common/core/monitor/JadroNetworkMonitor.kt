package ru.aidar.common.core.monitor

import kotlinx.coroutines.flow.Flow

interface JadroNetworkMonitor {
    val isOnline: Flow<Boolean>
}