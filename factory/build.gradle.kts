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
    implementation("nl.benfcasting.api:logicinterface")
    implementation("nl.benfcasting.api:logic")
    implementation("nl.benfcasting.api:repositoryinterface")
    implementation("nl.benfcasting.api:repository")
    implementation("nl.benfcasting.api:dalinterface")
    implementation("nl.benfcasting.api:dal")
    implementation("nl.benfcasting.api:model")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}