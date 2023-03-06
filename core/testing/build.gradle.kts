plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.testing"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation (Dependencies.koin_android)

    api (Dependencies.google_truth)
    api (Dependencies.junit)
    api (Dependencies.coroutines_test)

    api (Dependencies.android_junit)
    api (Dependencies.espresso)
    api (Dependencies.mockito_android)
    api (Dependencies.fragment_testing)
    api (Dependencies.espresso_contrib)
    api (Dependencies.uiautomator)
}