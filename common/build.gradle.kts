plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.aidar.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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

    // data store
    implementation(rootProject.extra["dataStoreDep"].toString())
    implementation(rootProject.extra["dataStoreCoreDep"].toString())

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

    implementation ("androidx.fragment:fragment-ktx:1.7.1")

    implementation("com.google.android.gms:play-services-location:21.2.0")

    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")


    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}