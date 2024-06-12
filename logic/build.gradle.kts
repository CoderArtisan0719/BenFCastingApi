plugins {
    kotlin("jvm") version "1.9.23"
}

group = "nl.benfcasting.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.inject:guice:7.0.0")
    testImplementation(kotlin("test"))
    implementation("nl.benfcasting.api:logicinterface")
    implementation("nl.benfcasting.api:repositoryinterface")
    implementation("nl.benfcasting.api:model")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}