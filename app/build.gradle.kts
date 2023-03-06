plugins {
    id ("com.android.application")
    kotlin ("android")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation (Dependencies.core_ktx)
    implementation (Dependencies.appcompat)
    implementation (Dependencies.material)
    implementation (Dependencies.constraintlayout)
    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.android_junit)
    androidTestImplementation (Dependencies.espresso)

    implementation (Dependencies.koin_android)

    implementation (Dependencies.navigation_fragment_ktx)
    implementation (Dependencies.navigation_ui_ktx)

    implementation(project(":feature:popular"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:profile"))
    implementation(project(":core:common"))
}