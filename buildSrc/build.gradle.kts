import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly("com.android.tools.build:gradle:7.4.0")
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