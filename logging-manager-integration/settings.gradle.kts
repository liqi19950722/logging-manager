rootProject.name = "logging-manager-integration"

pluginManagement {
    repositories.gradlePluginPortal()
    includeBuild("../build-logic")
}
dependencyResolutionManagement {
    repositories.mavenCentral()
}

includeBuild("../logging-manager")

include("servlet")
include("spring")
include("spring-utils")
include("spring-boot")