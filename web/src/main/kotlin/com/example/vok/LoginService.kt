package com.example.vok

import com.vaadin.flow.component.UI
import com.vaadin.flow.server.VaadinSession
import eu.vaadinonkotlin.vaadin10.Session
import java.io.Serializable

data class User(val name: String) : Serializable

class LoginService : Serializable {

    fun login(username: String, password: String): Boolean {
        currentUser = User(username)
        UI.getCurrent().page.reload()
        return true
    }

    var currentUser: User? = null
        private set

    fun logout() {
        VaadinSession.getCurrent().close()
        UI.getCurrent().navigate("")
        UI.getCurrent().page.reload()
    }

    val isLoggedIn get() = currentUser != null
}

val Session.loginService: LoginService get() = getOrPut { LoginService() }
