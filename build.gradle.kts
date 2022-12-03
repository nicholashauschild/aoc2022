import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.wrapper.Wrapper

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.6"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    val day: String? by project
    mainClass.set("aoc2022.Day_${day}Kt")
}