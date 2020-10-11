package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.Version

@Route("old-welcome", layout = MainLayout::class)
class WelcomeView: KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); content { align(center, middle) }; isMargin = false; isSpacing = true

            h1("Yay! You're on Vaadin-on-Kotlin!")
            image("images/chucknorris.jpg")
            div { html("<strong>Vaadin Server version: </strong> ${Version.getFullVersion()}") }
            div { html("<strong>Kotlin version: </strong> ${KotlinVersion.CURRENT}") }
            div { html("<strong>JVM version: </strong> $jvmVersion") }
        }
    }
}

val jvmVersion: String get() = System.getProperty("java.version")
