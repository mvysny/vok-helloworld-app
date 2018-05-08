package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.Version

@Route("", layout = MainLayout::class)
class WelcomeView: VerticalLayout() {
    init {
        setSizeFull(); content { align(center, middle) }
        isMargin = false
        verticalLayout {
            content { align(center, top) }
            isMargin = false; isSpacing = true
            h1("Yay! You're on Vaadin-on-Kotlin!")
            image("images/chucknorris.jpg")
            div { html("<strong>Vaadin Server version: </strong> ${Version.getFullVersion()}") }
            div { html("<strong>Kotlin version: </strong> ${KotlinVersion.CURRENT}") }
            div { html("<strong>JVM version: </strong> $jvmVersion") }
        }
    }
}

val jvmVersion: String get() = System.getProperty("java.version")
