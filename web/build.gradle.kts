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
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("org.slf4j:slf4j-api:1.7.36")

    // db
    implementation("org.flywaydb:flyway-core:8.5.12")
    implementation("com.h2database:h2:2.1.212")

    // REST
    implementation("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
}

