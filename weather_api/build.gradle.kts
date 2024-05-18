plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(rootProject.extra["retrofitConverterDep"].toString())
    implementation(rootProject.extra["retrofitDep"].toString())
    implementation(rootProject.extra["coroutinesDep"].toString())
}