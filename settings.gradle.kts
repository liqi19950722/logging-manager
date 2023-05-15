rootProject.name = "logging-manager-project"

pluginManagement {
    repositories.gradlePluginPortal()
}

dependencyResolutionManagement {
    repositories.mavenCentral()
}

includeBuild("spike")
includeBuild("logging-manager")
includeBuild("logging-manager-integration")