plugins {
    id("commons")
}


dependencies.constraints {
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
}

dependencies {
    implementation("io.github.logging.manager:logging-manager")
    implementation("jakarta.servlet:jakarta.servlet-api")
}
