buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
    repositories {
        google()
    }

    /* config */
    val compileSdk by extra { "34" }
    val minSdk by extra { "24" }
    val versionCode by extra { "1" }
    val applicationId by extra { "ru.aidar.jadrotest" }
    /* config */

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
    val materialVersion by extra { "1.12.0" }
    val viewModelVersion by extra { "2.7.0" }
    val runtimeVersion by extra { "2.7.0" }
    val composeVersion by extra { "1.6.7" }
    val activityComposeDep by extra { "androidx.activity:activity-compose:1.9.0" }
    val materialDep by extra { "com.google.android.material:material:$materialVersion" }
    val viewModelDep by extra { "androidx.lifecycle:lifecycle-viewmodel-compose:$viewModelVersion" }
    val runtimeDep by extra { "androidx.lifecycle:lifecycle-runtime-compose:$viewModelVersion" }
    val runtimeKtxDep by extra { "androidx.lifecycle:lifecycle-runtime-ktx:$viewModelVersion" }
    val composeUiDep by extra { "androidx.compose.ui:ui:$composeVersion" }
    /* compose */


    /* coroutines */
    val coroutinesVersion by extra { "1.8.0" }
    val coroutinesDep by extra { "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion" }
    val coroutinesAndroidDep by extra { "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion" }
    /* coroutines */


    /* core */
    val coreVersion by extra { "1.13.1" }
    val composeActivityVersion by extra { "1.9.0" }
    val appCompatVersion by extra { "1.6.1" }
    val fragmentVersion by extra { "1.7.1" }
    val coreDep by extra { "androidx.core:core-ktx:$coreVersion" }
    val appCompatDep by extra { "androidx.appcompat:appcompat:$appCompatVersion" }
    val fragmentDep by extra { "androidx.fragment:fragment-ktx:$fragmentVersion" }
    val composeActivityDep by extra { "androidx.activity:activity-compose:$composeActivityVersion" }
    /* core */


    /* location */
    val locationVersion by extra { "21.2.0" }
    val locationDep by extra { "com.google.android.gms:play-services-location:$locationVersion" }
    /* location */


    /* test */
    val junitVersion by extra { "4.13.2" }
    val androidJunitVersion by extra { "1.1.5" }
    val espressoVersion by extra { "3.5.1" }
    val junitDep by extra { "junit:junit:$junitVersion" }
    val androidJunitDep by extra { "androidx.test.ext:junit:$androidJunitVersion" }
    val espressoDep by extra { "androidx.test.espresso:espresso-core:$espressoVersion" }
    /* test */

    /* room */
    val roomVersion by extra { "2.6.1" }
    val roomRuntime by extra { "androidx.room:room-runtime:$roomVersion" }
    val roomKtxRuntime by extra { "androidx.room:room-ktx:$roomVersion" }
    val roomKspRuntime by extra { "androidx.room:room-compiler:$roomVersion" }
    /* room */

}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.android.library") version "8.2.2" apply false
    kotlin("plugin.serialization") version "1.9.22" apply false
}