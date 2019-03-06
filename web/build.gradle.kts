plugins {
    war
    id("org.gretty")
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
}

dependencies {
    compile("eu.vaadinonkotlin:vok-framework-v10-sql2o:${properties["vok_version"]}")
    compile(platform("com.vaadin:vaadin-bom:${properties["vaadin10_version"]}"))

    // logging
    // currently we are logging through the SLF4J API to LogBack. See logback.xml file for the logger configuration
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("org.slf4j:slf4j-api:1.7.25")

    // db
    compile("org.flywaydb:flyway-core:5.2.4")
    compile("com.h2database:h2:1.4.198")

    // REST
    compile("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    compile(kotlin("stdlib-jdk8"))

    // test support
    testCompile("com.github.mvysny.kaributesting:karibu-testing-v10:1.1.4")
    testCompile("com.github.mvysny.dynatest:dynatest-engine:0.15")
}
