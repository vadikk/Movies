plugins {
    id ("com.android.application")
    kotlin ("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.movies"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        applicationId = AndroidConfig.applicationID
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    val composeBom = platform(Dependencies.androidxComposeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation (Dependencies.core_ktx)
    implementation (Dependencies.compose_ui)
    implementation (Dependencies.compose_material)
    implementation (Dependencies.compose_ui_tooling_preview)
    implementation (Dependencies.material)

    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.android_junit)
    androidTestImplementation (Dependencies.espresso)

    implementation (Dependencies.hilt_android)
    kapt (Dependencies.hilt_kapt)

    implementation (Dependencies.navigation_compose)
    implementation (Dependencies.accompanist_nav_animation)

    implementation(project(":feature:popular"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:profile"))
    implementation(project(":core:common"))
}