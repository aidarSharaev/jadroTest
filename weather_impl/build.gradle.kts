plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.aidar.weather_impl"
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

    // project
    implementation(project(":weather_api"))
    implementation(project(":common"))

    // dagger
    implementation(rootProject.extra["daggerDep"].toString())
    ksp(rootProject.extra["daggerKspDep"].toString())

    // navigation
    implementation(rootProject.extra["navFragmentDep"].toString())
    implementation(rootProject.extra["navUiDep"].toString())

    // location
    implementation(rootProject.extra["locationDep"].toString())

    // jetpack
    implementation(rootProject.extra["activityComposeDep"].toString())
    implementation(rootProject.extra["viewModelDep"].toString())
    implementation(rootProject.extra["runtimeDep"].toString())
    implementation(rootProject.extra["composeUiDep"].toString())
    implementation("androidx.compose.runtime:runtime:1.6.7")
    implementation("androidx.compose.material3:material3:1.2.1")

    // remote
    implementation(rootProject.extra["retrofitDep"].toString())
    implementation(rootProject.extra["retrofitConverterDep"].toString())
    implementation(rootProject.extra["serializationDep"].toString())

    // coroutnies
    implementation(rootProject.extra["coroutinesAndroidDep"].toString())

    // core
    implementation(rootProject.extra["coreDep"].toString())
    implementation(rootProject.extra["appCompatDep"].toString())
    implementation(rootProject.extra["fragmentDep"].toString())

    // material
    implementation(rootProject.extra["materialDep"].toString())

    // room
    implementation(rootProject.extra["roomRuntime"].toString())
    implementation(rootProject.extra["roomKtxRuntime"].toString())
    annotationProcessor(rootProject.extra["roomKspRuntime"].toString())
    ksp(rootProject.extra["roomKspRuntime"].toString())

    // test
    testImplementation(rootProject.extra["junitDep"].toString())
    androidTestImplementation(rootProject.extra["androidJunitDep"].toString())
    androidTestImplementation(rootProject.extra["espressoDep"].toString())
}