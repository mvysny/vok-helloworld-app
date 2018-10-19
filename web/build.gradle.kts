plugins {
    id("io.spring.dependency-management") version "1.0.4.RELEASE"  // remove when https://github.com/gradle/gradle/issues/4417 is fixed
    war
    id("org.gretty")
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
}

dependencyManagement {
    imports { mavenBom("com.vaadin:vaadin-bom:${ext["vaadin10_version"]}") }
}

dependencies {
    compile("com.github.vaadinonkotlin:vok-framework-v10-sql2o:${ext["vok_version"]}")
    compile("com.github.vok.karibudsl:karibu-dsl-v10:${ext["karibudsl_version"]}")

    // logging
    // currently we are logging through the SLF4J API to LogBack. See logback.xml file for the logger configuration
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("org.slf4j:slf4j-api:1.7.25")

    // db
    compile("org.flywaydb:flyway-core:5.2.0")
    compile("com.h2database:h2:1.4.197")

    // REST
    compile("com.github.vaadinonkotlin:vok-rest:${ext["vok_version"]}")

    // Kotlin
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}
