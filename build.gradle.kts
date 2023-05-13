plugins {
    `java-library`
}

group = "io.github.logging.manager"
version = "0.0.1-SNAPSHOT"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}


val spike = sourceSets.create("spike") {

}


dependencies {
    implementation(platform("org.junit:junit-bom:5.9.3"))
    implementation(platform("org.mockito:mockito-bom:5.3.1"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.6"))

    "spikeImplementation"(platform("org.junit:junit-bom:5.9.3"))
    "spikeImplementation"(platform("org.mockito:mockito-bom:5.3.1"))
}

dependencies.constraints {
    implementation("ch.qos.logback:logback-classic:1.4.7")
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    add("spikeImplementation","ch.qos.logback:logback-classic:1.4.7")
}

dependencies {
    implementation("ch.qos.logback:logback-classic")
    implementation("jakarta.servlet:jakarta.servlet-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework:spring-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    "spikeImplementation"("ch.qos.logback:logback-classic")
    "spikeImplementation"("org.junit.jupiter:junit-jupiter")
    "spikeImplementation"("org.mockito:mockito-core")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>(spike.name) {
    classpath = spike.runtimeClasspath
    testClassesDirs = spike.output.classesDirs
    useJUnitPlatform()
}