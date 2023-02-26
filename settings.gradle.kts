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
rootProject.name = "Calorie"
include(":app")
include(":app-features")
include(":app-modules")
include(":app-features:presentation")
include(":app-modules:remote")
include(":app-modules:local")
include(":app-modules:data")
include(":app-features:calories")
include(":app-modules:ui")
include(":app-features:calorie")
