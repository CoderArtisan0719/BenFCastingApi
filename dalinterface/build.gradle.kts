plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "nl.benfcasting.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.google.inject:guice:7.0.0")
    testImplementation(kotlin("test"))
    implementation("nl.benfcasting.api:model")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}