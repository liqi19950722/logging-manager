plugins {
    `java-library`
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies.constraints {
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.9.3"))
    implementation(platform("org.mockito:mockito-bom:5.3.1"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.6"))

    implementation(project(":spring-utils"))
    implementation("io.github.logging.manager:logging-manager")
    implementation("org.springframework:spring-context")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework:spring-test")

}

tasks.test {
    useJUnitPlatform()
}