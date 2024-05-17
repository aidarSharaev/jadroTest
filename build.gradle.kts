buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
    repositories {
        google()
    }

    /* remote */
    val retrofitVersion by extra { "2.9.0" }
    val okhttpVersion by extra { "4.12.0" }
    val serializationVersion by extra { "1.6.0" }
    val retrofitDep by extra { "com.squareup.retrofit2:retrofit:$retrofitVersion" }
    val retrofitConverterDep by extra { "com.squareup.retrofit2:converter-gson:$retrofitVersion" }
    val serializationDep by extra { "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion" }
    val okhttpDep by extra { "com.squareup.okhttp3:okhttp:$okhttpVersion" }
    val interceptorDep by extra { "com.squareup.okhttp3:logging-interceptor:$okhttpVersion" }
    /* remote */


    /* dagger */
    val daggerVersion by extra { "2.50" }
    val daggerDep by extra { "com.google.dagger:dagger:$daggerVersion" }
    val daggerKspDep by extra { "com.google.dagger:dagger-compiler:$daggerVersion" }
    /* dagger */


    /* navigation */
    val navigationVersion by extra { "2.7.7" }
    val navFragmentDep by extra { "androidx.navigation:navigation-fragment-ktx:$navigationVersion" }
    val navUiDep by extra { "androidx.navigation:navigation-ui-ktx:$navigationVersion" }
    /* navigation */


    /* data store */
    val dataStoreVersion by extra { "1.0.0" }
    val dataStoreDep by extra { "androidx.datastore:datastore-preferences:$dataStoreVersion" }
    val dataStoreCoreDep by extra { "androidx.datastore:datastore-preferences-core:$dataStoreVersion" }
    /* data store */

    /* compose */
    val activityComposeVersion by extra { "1.9.0" }
    val viewModelVersion by extra { "2.7.0" }
    val activityComposeDep by extra { "androidx.activity:activity-compose:1.9.0"}
    val viewModelDep by extra { "androidx.lifecycle:lifecycle-viewmodel-compose:$viewModelVersion"}
    /* compose */


}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.android.library") version "8.2.2" apply false
    kotlin("plugin.serialization") version "1.9.22" apply false
}