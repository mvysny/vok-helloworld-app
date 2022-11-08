package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.router.Route

@Route("", layout = MainLayout::class)
class MyWelcomeView: KComposite() {
    private val root = ui {
        verticalLayout {
            h1("Hello, Vaadin-on-Kotlin!")
            routerLink(text = "My Blog", viewType = ArticlesView::class)
        }
    }
}
