package com.example.vok

import com.github.mvysny.kaributesting.v10.MockVaadin
import com.github.mvysny.kaributesting.v10.Routes
import eu.vaadinonkotlin.VaadinOnKotlin
import eu.vaadinonkotlin.vaadin.vokdb.dataSource
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

/**
 * One-shot per-JVM app bootstrap: touching this object's class triggers
 * `Bootstrap.contextInitialized` exactly once.
 */
private object AppBootstrap {
    init {
        Bootstrap().contextInitialized(null)
    }
}

/**
 * Base class for browserless UI tests using Karibu-Testing. Initialises the
 * app once per JVM, scans the routes once per test class, resets the database
 * to seed state before each test, and tears down the mocked Vaadin session
 * afterwards.
 */
abstract class AbstractAppTest {

    companion object {
        private lateinit var routes: Routes

        @BeforeAll
        @JvmStatic
        fun initSuite() {
            AppBootstrap
            routes = Routes().autoDiscoverViews("com.example.vok")
        }
    }

    @BeforeEach
    fun resetDbAndMockVaadin() {
        Flyway.configure()
            .dataSource(VaadinOnKotlin.dataSource)
            .cleanDisabled(false)
            .load()
            .apply { clean(); migrate() }
        MockVaadin.setup(routes)
    }

    @AfterEach
    fun teardownMockVaadin() {
        MockVaadin.tearDown()
    }
}
