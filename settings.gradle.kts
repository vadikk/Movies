pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Movies"
include (":app")
include (":core:common")
include (":core:network")
include (":core:testing")
include (":data:movie")
include (":feature:popular")
include (":feature:detail")
include (":common_ui_res")
include (":core:database")
include (":feature:favorite")
include (":feature:profile")
