package ru.aidar.jadrotest.deps

import android.app.Activity
import dagger.MapKey
import ru.aidar.jadrotest.JadroApplication
import kotlin.reflect.KClass

interface ComponentDependencies

typealias ComponentDependenciesProvider =
    Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val value: KClass<out ComponentDependencies>)

inline fun <reified T : ComponentDependencies> Activity.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

fun Activity.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    return (application as? JadroApplication)?.dependencies
        ?: throw IllegalStateException("Can not find suitable dagger provider for $this")
}