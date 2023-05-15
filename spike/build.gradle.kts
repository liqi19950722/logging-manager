plugins {
    java
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies.constraints {
    implementation("ch.qos.logback:logback-classic:1.4.7")
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.9.3"))
    implementation(platform("org.mockito:mockito-bom:5.3.1"))
    testImplementation("ch.qos.logback:logback-classic")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core")
}

tasks.test {
    useJUnitPlatform()
}