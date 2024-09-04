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
    implementation("nl.benfcasting.api:model")
    implementation("nl.benfcasting.api:dalinterface")
    implementation("nl.benfcasting.api:repositoryinterface")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}