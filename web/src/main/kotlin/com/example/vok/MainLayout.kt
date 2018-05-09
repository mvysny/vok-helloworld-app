package com.example.vok

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.BodySize
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.RouterLayout

/**
 * The main layout.
 */
@BodySize(width = "100vw", height = "100vh")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes")
class MainLayout : VerticalLayout(), RouterLayout {

    init {
        setSizeFull()
    }
}

