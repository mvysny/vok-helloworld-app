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
    // Vaadin 14
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}") {
        // Webjars are only needed when running in Vaadin 13 compatibility mode
        listOf("com.vaadin.webjar", "org.webjars.bowergithub.insites",
                "org.webjars.bowergithub.polymer", "org.webjars.bowergithub.polymerelements",
                "org.webjars.bowergithub.vaadin", "org.webjars.bowergithub.webcomponents")
                .forEach { exclude(group = it) }
    }
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")

    implementation("com.zaxxer:HikariCP:4.0.3")

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
    implementation("org.flywaydb:flyway-core:7.14.1")
    implementation("com.h2database:h2:1.4.200")

    // REST
    implementation("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // test support
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v10:1.3.0")
    testImplementation("com.github.mvysny.dynatest:dynatest-engine:0.20")
}

vaadin {
    pnpmEnable = false  // workaround for https://github.com/vaadin/flow/issues/10571
}
