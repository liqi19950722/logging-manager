plugins {
    `java-library`
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.6"))
}

dependencies {
    implementation("io.github.logging.manager:logging-manager")
    implementation("org.springframework:spring-context")
}