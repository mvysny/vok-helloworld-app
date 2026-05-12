package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.router.Route

@Route("")
class WelcomeView: KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); content { align(center, middle) }; isMargin = false; isSpacing = true

            h1("Welcome to BoltShop")
        }
    }
}
