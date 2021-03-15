package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.dependency.NpmPackage
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.shrinkwrap.VaadinCoreShrinkWrap

/**
 * Shows a simple yes-no confirmation dialog, with given [text] and [title]. When user clicks the `Yes` button,
 * [yesListener] is invoked.
 * @param text defaults to "Are you sure?"
 */
fun confirmDialog(text: String = "Are you sure?", title: String? = null, yesListener: ()->Unit) {
    val window = Dialog()
    window.apply {
        setSizeUndefined()

        if (title != null) h2(title)
        text(text)
        horizontalLayout {
            button("Yes") {
                onLeftClick { yesListener(); window.close() }
                setPrimary()
            }
            button("No") {
                onLeftClick { window.close() }
            }
        }
    }
    window.open()
}

val jvmVersion: String get() = System.getProperty("java.version")
val vaadinVersion: String get() {
    val annotation: NpmPackage = checkNotNull(
        VaadinCoreShrinkWrap::class.java.getAnnotation(NpmPackage::class.java),
        { "We only support Vaadin 14 and higher" })
    return annotation.version
}
