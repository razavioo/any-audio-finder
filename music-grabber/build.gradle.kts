import java.util.*

plugins {
    id("java")
    kotlin("jvm") version "1.7.10"
    id("maven-publish")
}

group = "dev.emad"
version = "0.0.1"

repositories {
    mavenCentral()
}

publishing {
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
            groupId = "dev.emad"
            artifactId = "music-grabber"
            version = "0.0.1"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/razavioo/any-audio-finder")
            credentials {
                val properties = Properties().apply {
                    load(File("local.properties").inputStream())
                }
                username = properties.getProperty("gpr.user")
                password = properties.getProperty("gpr.token")
            }
        }
    }
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