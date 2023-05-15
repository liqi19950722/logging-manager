rootProject.name = "logging-manager-integration"
dependencyResolutionManagement {
    repositories.mavenCentral()
}
includeBuild("../logging-manager")
include("servlet")
include("spring")
include("spring-utils")
include("spring-boot")