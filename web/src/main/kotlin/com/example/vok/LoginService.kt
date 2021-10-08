package com.example.vok

import com.vaadin.flow.component.UI
import com.vaadin.flow.server.VaadinRequest
import com.vaadin.flow.server.VaadinService
import com.vaadin.flow.server.VaadinSession
import eu.vaadinonkotlin.vaadin10.Session
import java.io.Serializable

data class User(val name: String) : Serializable

class LoginService : Serializable {

    fun login(username: String, password: String): Boolean {
        currentUser = User(username)

        // creates a new session after login, to prevent session fixation attack
        VaadinService.reinitializeSession(VaadinRequest.getCurrent())

        // this will cause the UI to be re-created. Since the user is now logged in and present in the session,
        // the UI should now initialize properly and should not show the LoginView.
        UI.getCurrent().page.reload()
        return true
    }

    var currentUser: User? = null
        private set

    fun logout() {
        Session.current.close()
        // The UI is recreated by the page reload, and since there is no user in the session (since it has been cleared),
        // the UI will show the LoginView.
        UI.getCurrent().page.reload()
    }

    val isLoggedIn get() = currentUser != null
}

val Session.loginService: LoginService get() = getOrPut { LoginService() }
