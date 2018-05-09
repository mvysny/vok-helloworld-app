package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("", layout = MainLayout::class)
class MyWelcomeView: VerticalLayout() {
    init {
        h1("Hello, Vaadin-on-Kotlin!")
    }
}
