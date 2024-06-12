plugins {
    kotlin("jvm") version "1.9.23"
}

group = "nl.benfcasting.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("nl.benfcasting.api:model")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}