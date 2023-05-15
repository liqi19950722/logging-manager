plugins {
    id("commons")
}


dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.6"))
}

dependencies {
    implementation("io.github.logging.manager:logging-manager")
    implementation("org.springframework:spring-context")
}