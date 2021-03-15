package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.dependency.NpmPackage
import com.vaadin.flow.router.Route
import com.vaadin.shrinkwrap.VaadinCoreShrinkWrap

@Route("")
class WelcomeView: KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); content { align(center, middle) }; isMargin = false; isSpacing = true

            h1("Yay! You're on Vaadin-on-Kotlin!")
            image("images/chucknorris.jpg")
            div { html("<strong>Vaadin version: </strong> $vaadinVersion") }
            div { html("<strong>Kotlin version: </strong> ${KotlinVersion.CURRENT}") }
            div { html("<strong>JVM version: </strong> $jvmVersion") }
        }
    }
}

val jvmVersion: String get() = System.getProperty("java.version")
val vaadinVersion: String get() {
    val annotation: NpmPackage = checkNotNull(VaadinCoreShrinkWrap::class.java.getAnnotation(NpmPackage::class.java),
        { "We only support Vaadin 14 and higher" })
    return annotation.version
}
