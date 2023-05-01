plugins {
    id ("com.android.library")
    kotlin ("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
    configurations {
        all {
            exclude(group = "androidx.lifecycle", module = "lifecycle-viewmodel-ktx")
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:common"))

    implementation (Dependencies.hilt_android)
    kapt (Dependencies.hilt_kapt)

    implementation(Dependencies.paging)

    androidTestImplementation(project(":core:testing"))
    kaptAndroidTest (Dependencies.hilt_android_compiler)
}