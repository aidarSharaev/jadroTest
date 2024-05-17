package ru.aidar.common.core.config

data class JadroNetworkProperties(
    val connectTimeout: Long,
    val readTimeout: Long,
    val writeTimeout: Long,
)
