package ru.aidar.weather_api.model

interface ApiResult {
    object Success : ApiResult
    object Fail : ApiResult
    object Error : ApiResult
}