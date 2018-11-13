package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.dialog.Dialog

/**
 * Shows a simple yes-no confirmation dialog, with given [text] and [title]
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
