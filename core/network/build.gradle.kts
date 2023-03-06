import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id ("com.android.library")
    kotlin ("android")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.koin_android)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_moshi)

    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttp_interceptor)

}