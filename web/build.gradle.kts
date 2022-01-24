plugins {
    war
    id("org.gretty")
    id("com.vaadin")
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
}

dependencies {
    implementation("eu.vaadinonkotlin:vok-framework-vokdb:${properties["vok_version"]}")
    // Vaadin
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}") {
        exclude(module = "fusion-endpoint") // exclude fusion: it brings tons of dependencies (including swagger)
    }
    providedCompile("javax.servlet:javax.servlet-api:4.0.1")

    implementation("com.zaxxer:HikariCP:5.0.1")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:1.7.32")
    implementation("org.slf4j:slf4j-api:1.7.32")

    // validation
    implementation("org.hibernate.validator:hibernate-validator:6.1.4.Final") {
        exclude(module = "jakarta.validation-api")
    }
    // EL is required: http://hibernate.org/validator/documentation/getting-started/
    implementation("org.glassfish:javax.el:3.0.1-b11")

    // db
    implementation("org.flywaydb:flyway-core:8.4.1")
    implementation("com.h2database:h2:2.1.210")

    // REST
    implementation("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // test support
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v10:1.3.4")
    testImplementation("com.github.mvysny.dynatest:dynatest-engine:0.20")
}

vaadin {
    nodeVersion = "v16.13.2" // workaround for https://github.com/vaadin/flow/issues/12732
}
