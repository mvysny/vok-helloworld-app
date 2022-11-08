plugins {
    application
    id("com.vaadin")
}

dependencies {
    // Vaadin
    implementation("eu.vaadinonkotlin:vok-framework-vokdb:${properties["vok_version"]}")
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}")
    implementation("com.github.mvysny.vaadin-boot:vaadin-boot:10.1")

    implementation("com.zaxxer:HikariCP:5.0.1")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:2.0.0")

    // validation
    implementation("org.hibernate.validator:hibernate-validator:6.1.4.Final") {
        exclude(module = "jakarta.validation-api")
    }
    // EL is required: http://hibernate.org/validator/documentation/getting-started/
    implementation("org.glassfish:javax.el:3.0.1-b11")

    // db
    implementation("org.flywaydb:flyway-core:9.5.1")
    implementation("com.h2database:h2:2.1.214")

    // REST
    implementation("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // test support
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v10:1.3.15")
    testImplementation("com.github.mvysny.dynatest:dynatest-engine:0.24")
}

application {
    mainClass.set("com.example.vok.MainKt")
}
