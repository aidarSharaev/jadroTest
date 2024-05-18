package ru.aidar.common.data.local.di

import ru.aidar.common.data.local.JadroDatabase

interface DbApi {
    fun provideDatabase(): JadroDatabase
}