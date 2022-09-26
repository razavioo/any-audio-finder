plugins {
    id("java")
    kotlin("jvm") version "1.7.10"
}

group = "dev.emad"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jsoup:jsoup:1.15.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}