import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id ("com.android.library")
    kotlin ("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.network"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
        consumerProguardFiles(AndroidConfig.consumerProguardFiles)

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String","TMBD_API_KEY", properties.getProperty("API_KEY"))
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation (Dependencies.hilt_android)
    kapt (Dependencies.hilt_kapt)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_moshi)

    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttp_interceptor)

}