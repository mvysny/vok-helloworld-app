package com.example.vok

import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.component.page.Viewport
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import eu.vaadinonkotlin.VaadinOnKotlin
import eu.vaadinonkotlin.rest.VokRest
import eu.vaadinonkotlin.rest.configureToJavalin
import eu.vaadinonkotlin.rest.gsonMapper
import eu.vaadinonkotlin.vokdb.dataSource
import io.javalin.Javalin
import io.javalin.http.servlet.JavalinServlet
import org.flywaydb.core.Flyway
import org.h2.Driver
import org.slf4j.LoggerFactory
import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import jakarta.servlet.annotation.WebListener
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * Called by the Servlet Container to bootstrap your app. We need to bootstrap the Vaadin-on-Kotlin framework,
 * in order to have support for the database; then we'll run Flyway migration scripts, to make sure that the database is up-to-date.
 * After that's done, your app is ready to be serving client browsers.
 */
@WebListener
class Bootstrap: ServletContextListener {
    override fun contextInitialized(sce: ServletContextEvent?) {
        log.info("Starting up")

        // this will configure your database. For demo purposes, an in-memory embedded H2 database is used. To use a production-ready database:
        // 1. fill in the proper JDBC URL here
        // 2. make sure to include the database driver into the classpath, by adding a dependency on the driver into the build.gradle file.
        val cfg = HikariConfig().apply {
            driverClassName = Driver::class.java.name
            jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
            username = "sa"
            password = ""
        }
        VaadinOnKotlin.dataSource = HikariDataSource(cfg)

        // Initializes the VoK framework
        log.info("Initializing VaadinOnKotlin")
        VaadinOnKotlin.init()

        // Makes sure the database is up-to-date
        log.info("Running DB migrations")
        val flyway = Flyway.configure()
            .dataSource(VaadinOnKotlin.dataSource)
            .load()
        flyway.migrate()
        log.info("Initialization complete")
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        log.info("Shutting down")
        log.info("Destroying VaadinOnKotlin")
        VaadinOnKotlin.destroy()
        log.info("Shutdown complete")
    }

    companion object {
        private val log = LoggerFactory.getLogger(Bootstrap::class.java)
    }
}

/**
 * Provides access to REST services. Uses the Javalin library to export the REST services; the services are configured
 * in the [configureRest] method.
 */
@WebServlet(urlPatterns = ["/rest/*"], name = "JavalinRestServlet", asyncSupported = false)
class JavalinRestServlet : HttpServlet() {
    val javalin: JavalinServlet = Javalin.createStandalone()
            .configureRest()
            .javalinServlet()

    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        javalin.service(req, resp)
    }
}

fun Javalin.configureRest(): Javalin {
    gsonMapper(VokRest.gson)
    return this
}

@Viewport(Viewport.DEVICE_DIMENSIONS)
class AppShell: AppShellConfigurator
