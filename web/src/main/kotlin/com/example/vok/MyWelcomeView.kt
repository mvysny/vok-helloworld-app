package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("")
class MyWelcomeView: VerticalLayout() {
    init {
        verticalLayout {
            h1("Hello, Vaadin-on-Kotlin!")
            routerLink(text = "My Blog", viewType = ArticlesView::class)
        }
    }
}