package plugin

import AndroidConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()

            extensions.configure<ApplicationExtension> {
                applyAndroidConfig(this)
                applyJavaExtension(this)

                defaultConfig.targetSdk = AndroidConfig.targetSdkVersion
                defaultConfig.applicationId = AndroidConfig.applicationID
                defaultConfig.versionCode = AndroidConfig.versionCode
                defaultConfig.versionName = AndroidConfig.versionName
            }
        }
    }

    private fun Project.applyPlugins() {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }
    }

    private fun Project.applyJavaExtension(commonExtension: CommonExtension<*, *, *, *>) {
        commonExtension.apply {
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }

        (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
            )

            // Set JVM target to 1.8
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    private fun Project.applyAndroidConfig(commonExtension: CommonExtension<*, *, *, *>) {
        commonExtension.apply {
            compileSdk = AndroidConfig.compileSdkVersion

            defaultConfig {
                minSdk = AndroidConfig.minSdkVersion
                testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
            }
        }
    }
}