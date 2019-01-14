package com.example.vok

import com.github.mvysny.karibudsl.v10.*
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

            loginForm = loginForm("Hello!") {
                classNames.add("loginform")
                text("Log in as user/user or admin/admin")
                onLogin { username, password ->
                    if (!Session.loginService.login(username, password)) {
                        usernameField.isInvalid = true
                        usernameField.errorMessage = "No such user or invalid password"
                        passwordField.isInvalid = true
                        passwordField.errorMessage = "No such user or invalid password"
                    }
                }
            }
        }
    }
}
