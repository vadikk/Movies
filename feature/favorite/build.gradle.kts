plugins {
    id ("com.android.library")
    kotlin ("android")
}

android {
    namespace = "com.example.favorite"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
        consumerProguardFiles(AndroidConfig.consumerProguardFiles)
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
    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.android_junit)
    androidTestImplementation (Dependencies.espresso)

    implementation (Dependencies.navigation_fragment_ktx)
    implementation (Dependencies.navigation_ui_ktx)

    implementation(Dependencies.coil)

    implementation (Dependencies.koin_android)

    implementation(project(":data:movie"))
    implementation(project(":core:common"))
    implementation(project(":common_ui_res"))
}