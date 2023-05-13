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
    "spikeImplementation"(platform("org.junit:junit-bom:5.9.3"))
    "spikeImplementation"(platform("org.mockito:mockito-bom:5.3.1"))
}

dependencies.constraints {
    implementation("ch.qos.logback:logback-classic:1.4.7")
    add("spikeImplementation","ch.qos.logback:logback-classic:1.4.7")
}

dependencies {
    implementation("ch.qos.logback:logback-classic")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core")
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