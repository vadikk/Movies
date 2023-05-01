plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.testing"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation (project(":core:common"))

    implementation (Dependencies.hilt_android)
    api (Dependencies.hilt_android_testing)
    kaptAndroidTest (Dependencies.hilt_android_compiler)

    api (Dependencies.google_truth)
    api (Dependencies.junit)
    api (Dependencies.coroutines_test)
    api (Dependencies.compose_ui_test_junit4)

    api (Dependencies.android_junit)
    api (Dependencies.espresso)
    api (Dependencies.mockito_android)
    api (Dependencies.fragment_testing)
    api (Dependencies.espresso_contrib)
    api (Dependencies.uiautomator)
}