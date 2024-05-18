plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(rootProject.extra["retrofitConverterDep"].toString())
    implementation(rootProject.extra["retrofitDep"].toString())
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}