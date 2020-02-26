package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.login.*
import com.vaadin.flow.router.*
import eu.vaadinonkotlin.vaadin10.*

@Route("login")
class LoginView : KComposite(), BeforeEnterObserver {

    override fun beforeEnter(event: BeforeEnterEvent) {
        if (Session.loginService.isLoggedIn) {
            event.rerouteTo("")
        }
    }

    private lateinit var loginForm: LoginForm
    private val root = ui {
        verticalLayout {
            setSizeFull(); isPadding = false; content { center() }

            val loginI18n: LoginI18n = loginI18n {
                header.title = "Hello!"
                additionalInformation = "Log in as user/user or admin/admin"
            }
            loginForm = loginForm(loginI18n) {
                addLoginListener { e ->
                    if (!Session.loginService.login(e.username, e.password)) {
                        isError = true
                    }
                }
            }
        }
    }
}
