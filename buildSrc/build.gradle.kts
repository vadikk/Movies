import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.0.0")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "movies.android.application"
            implementationClass = "plugin.AndroidLibraryPlugin"
        }
    }
}