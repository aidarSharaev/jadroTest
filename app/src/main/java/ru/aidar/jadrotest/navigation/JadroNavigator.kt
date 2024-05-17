package ru.aidar.jadrotest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import ru.aidar.weather_impl.WeatherRouter

class JadroNavigator : WeatherRouter {

    private var hgAppNavController: NavController? = null

    fun attachNavController(
        navController: NavController,
        graph: NavGraph?,
    ) {
        navController.setGraph(graph!!, null)
        hgAppNavController = navController
    }

    fun detachNavController(
        navController: NavController,
    ) {
        if(hgAppNavController == navController)
            hgAppNavController = null
    }
}