plugins {
    id ("com.android.library")
    kotlin ("android")
}

android {
    namespace = "com.example.movie"
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
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:common"))

    implementation (Dependencies.koin_android)

    implementation(Dependencies.paging)

    androidTestImplementation(project(":core:testing"))
}