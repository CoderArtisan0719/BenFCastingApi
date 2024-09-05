plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.24"
}

group = "nl.benfcasting.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
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