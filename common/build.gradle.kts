plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.aidar.common"
    compileSdk = rootProject.extra["compileSdk"].toString().toInt()

    defaultConfig {
        minSdk = rootProject.extra["minSdk"].toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // dagger
    implementation(rootProject.extra["daggerDep"].toString())
    ksp(rootProject.extra["daggerKspDep"].toString())

    // remote
    implementation(rootProject.extra["retrofitDep"].toString())
    implementation(rootProject.extra["retrofitConverterDep"].toString())
    implementation(rootProject.extra["serializationDep"].toString())
    implementation(rootProject.extra["okhttpDep"].toString())
    implementation(rootProject.extra["interceptorDep"].toString())

    // compose
    implementation("androidx.compose.runtime:runtime:1.6.7")
    implementation("androidx.compose.foundation:foundation-layout-android:1.6.7")
    implementation("androidx.compose.ui:ui:1.6.7")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-android:1.6.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // navigation
    implementation(rootProject.extra["navFragmentDep"].toString())
    implementation(rootProject.extra["navUiDep"].toString())

    // location
    implementation(rootProject.extra["locationDep"].toString())

    // room
    implementation(rootProject.extra["roomRuntime"].toString())
    implementation(rootProject.extra["roomKtxRuntime"].toString())
    annotationProcessor(rootProject.extra["roomKspRuntime"].toString())
    ksp(rootProject.extra["roomKspRuntime"].toString())

    // core
    implementation(rootProject.extra["coreDep"].toString())
    implementation(rootProject.extra["appCompatDep"].toString())
    implementation(rootProject.extra["fragmentDep"].toString())

    // material
    implementation(rootProject.extra["materialDep"].toString())

    // test
    testImplementation(rootProject.extra["junitDep"].toString())
    androidTestImplementation(rootProject.extra["androidJunitDep"].toString())
    androidTestImplementation(rootProject.extra["espressoDep"].toString())
}