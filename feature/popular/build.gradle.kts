plugins {
    id ("com.android.library")
    kotlin ("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.popular"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion

        testInstrumentationRunner = "com.example.testing.InstrumentationTestRunner"
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

    implementation (Dependencies.core_ktx)
    implementation (Dependencies.compose_ui)
    implementation (Dependencies.compose_material)
    implementation (Dependencies.compose_ui_tooling_preview)
    implementation (Dependencies.material)

    implementation (Dependencies.accompanist_nav_animation)

    implementation(Dependencies.paging_compose)

    implementation(Dependencies.coil_compose)

    implementation (Dependencies.hilt_android)
    kapt (Dependencies.hilt_kapt)
    implementation (Dependencies.hilt_navigation_compose)

    implementation(project(":data:movie"))
    implementation(project(":core:common"))
    implementation(project(":common_ui_res"))

    androidTestImplementation(project(":core:testing"))
    debugImplementation (Dependencies.compose_ui_test_manifest)
    kaptAndroidTest (Dependencies.hilt_android_compiler)
    debugImplementation (Dependencies.compose_ui_tooling_debug)
}