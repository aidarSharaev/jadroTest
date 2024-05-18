plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = rootProject.extra["applicationId"].toString()
    compileSdk = rootProject.extra["compileSdk"].toString().toInt()

    defaultConfig {
        applicationId = rootProject.extra["applicationId"].toString()
        minSdk = rootProject.extra["minSdk"].toString().toInt()
        targetSdk = rootProject.extra["compileSdk"].toString().toInt()
        versionCode = rootProject.extra["versionCode"].toString().toInt()
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // project
    implementation(project(":common"))
    implementation(project(":weather_api"))
    implementation(project(":weather_impl"))

    // dagger
    implementation(rootProject.extra["daggerDep"].toString())
    ksp(rootProject.extra["daggerKspDep"].toString())

    // navigation
    implementation(rootProject.extra["navFragmentDep"].toString())
    implementation(rootProject.extra["navUiDep"].toString())

    // remote
    implementation(rootProject.extra["retrofitDep"].toString())
    implementation(rootProject.extra["retrofitConverterDep"].toString())
    implementation(rootProject.extra["serializationDep"].toString())
    implementation(rootProject.extra["interceptorDep"].toString())

    // core
    implementation(rootProject.extra["coreDep"].toString())

    implementation(rootProject.extra["composeActivityDep"].toString())
    implementation(rootProject.extra["composeUiDep"].toString())
    implementation(rootProject.extra["runtimeKtxDep"].toString())
    implementation("androidx.compose.material3:material3:1.2.1")

    // test
    testImplementation(rootProject.extra["junitDep"].toString())
    androidTestImplementation(rootProject.extra["androidJunitDep"].toString())
    androidTestImplementation(rootProject.extra["espressoDep"].toString())
}