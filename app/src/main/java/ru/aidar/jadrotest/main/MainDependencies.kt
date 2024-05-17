package ru.aidar.jadrotest.main

import ru.aidar.jadrotest.deps.ComponentDependencies
import ru.aidar.jadrotest.navigation.JadroNavigator

interface MainDependencies: ComponentDependencies {
    fun navigator(): JadroNavigator
}