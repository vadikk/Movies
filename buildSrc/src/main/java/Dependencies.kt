object Dependencies {

    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"

    const val android_gradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlin_gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    //ui
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"

    //shimmer_koleton
    const val shimmer_koleton = "com.ericktijerou.koleton:koleton:${Versions.shimmer_koleton}"

    //coil
    const val coil = "io.coil-kt:coil:${Versions.coil}"

    //pagination
    const val paging = "androidx.paging:paging-runtime:${Versions.paging_version}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    //okhttp3
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //room
    const val android_room_runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val android_room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val android_room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
    const val android_room_paging = "androidx.room:room-paging:${Versions.room_version}"

    //navigation
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"

    //hilt
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_android}"
    const val hilt_kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt_android}"
    const val hilt_android_testing = "com.google.dagger:hilt-android-testing:${Versions.hilt_android}"
    const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_android}"

    //test
    const val junit = "junit:junit:${Versions.junit}"
    const val android_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val google_truth = "com.google.truth:truth:${Versions.google_truth}"
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"
    const val fragment_testing = "androidx.fragment:fragment-testing:${Versions.fragment_testing}"
    const val mockito_android = "org.mockito:mockito-android:${Versions.mockito_android}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_contrib}"
    const val uiautomator = "androidx.test.uiautomator:uiautomator:${Versions.uiautomator}"
}