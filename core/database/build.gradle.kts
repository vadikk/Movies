plugins {
    id ("com.android.library")
    kotlin ("android")
    id ("kotlin-kapt")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation(Dependencies.koin_android)

    androidTestImplementation(project(":core:testing"))
}