plugins {
    id ("com.android.library")
    kotlin ("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.common"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
        consumerProguardFiles(AndroidConfig.consumerProguardFiles)
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
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    val composeBom = platform(Dependencies.androidxComposeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation (Dependencies.compose_ui)
    implementation (Dependencies.compose_animation)
    implementation (Dependencies.compose_material)
    implementation (Dependencies.compose_ui_tooling_preview)
    debugImplementation (Dependencies.compose_ui_tooling_debug)

    implementation (Dependencies.coil_compose)

    implementation (Dependencies.hilt_android)
    kapt (Dependencies.hilt_kapt)
}