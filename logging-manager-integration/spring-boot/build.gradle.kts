plugins {
    id("commons")
}

dependencies {

    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.6"))

    implementation(project(":spring-utils"))
    implementation("io.github.logging.manager:logging-manager")
    implementation("org.springframework.boot:spring-boot")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
