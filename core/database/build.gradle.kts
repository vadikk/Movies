plugins {
    id ("com.android.library")
    kotlin ("android")
    id ("kotlin-kapt")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.database"
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
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation (Dependencies.core_ktx)
    implementation (Dependencies.appcompat)
    implementation (Dependencies.material)

    api(Dependencies.android_room_runtime)
    kapt(Dependencies.android_room_compiler)
    implementation(Dependencies.android_room_ktx)
    implementation(Dependencies.android_room_paging)

    implementation (Dependencies.hilt_android)
    kapt (Dependencies.hilt_kapt)

    androidTestImplementation(project(":core:testing"))
    kaptAndroidTest (Dependencies.hilt_android_compiler)
}