plugins {
    id ("com.android.library")
    kotlin ("android")
}

android {
    namespace = "com.example.common_ui_res"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
        consumerProguardFiles(AndroidConfig.consumerProguardFiles)
    }
}