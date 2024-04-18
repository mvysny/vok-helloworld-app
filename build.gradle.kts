import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.vaadin")
    application
}

defaultTasks("clean", "build")

repositories {
    mavenCentral()
}

dependencies {
    // Vaadin
    implementation("eu.vaadinonkotlin:vok-framework-vokdb:${properties["vok_version"]}")
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}") {
        afterEvaluate {
            if (vaadin.effective.productionMode.get()) {
                exclude(module = "vaadin-dev")
            }
        }
    }
    implementation("com.github.mvysny.vaadin-boot:vaadin-boot:12.2")

    implementation("com.zaxxer:HikariCP:5.1.0")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:2.0.12")

    // db
    implementation("org.flywaydb:flyway-core:10.11.0")
    implementation("com.h2database:h2:2.2.224")

    // REST
    implementation("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        // to see the exception stacktraces of failed tests in CI
        exceptionFormat = TestExceptionFormat.FULL
    }
}

application {
    mainClass.set("com.example.vok.MainKt")
}
