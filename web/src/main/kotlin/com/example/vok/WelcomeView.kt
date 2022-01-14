package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.VaadinVersion
import com.vaadin.flow.router.Route

@Route("old-welcome", layout = MainLayout::class)
class WelcomeView: KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); content { align(center, middle) }; isMargin = false; isSpacing = true

            h1("Yay! You're on Vaadin-on-Kotlin!")
            image("images/chucknorris.jpg")
            div { html("<strong>Vaadin version: </strong> ${VaadinVersion.get}") }
            div { html("<strong>Kotlin version: </strong> ${KotlinVersion.CURRENT}") }
            div { html("<strong>JVM version: </strong> $jvmVersion") }
        }
    }
}
