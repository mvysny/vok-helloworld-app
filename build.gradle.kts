import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.20"
    alias(libs.plugins.vaadin)
    application
}

defaultTasks("clean", "build")

repositories {
    mavenCentral()
}

dependencies {
    // Vaadin
    implementation(libs.vok.db)
    implementation(libs.vaadin.core) {
        if (vaadin.effective.productionMode.get()) {
            exclude(module = "vaadin-dev")
        }
    }
    implementation(libs.vaadin.boot)

    implementation(libs.hikaricp)

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation(libs.slf4j.simple)

    // validation
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    // EL is required: http://hibernate.org/validator/documentation/getting-started/
    implementation("org.glassfish:jakarta.el:4.0.2")

    // db
    implementation(libs.flyway)
    implementation(libs.h2)

    // REST
    implementation(libs.vok.rest)

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // test support
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v23:2.0.2")
    testImplementation("com.github.mvysny.dynatest:dynatest-engine:0.24")
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
