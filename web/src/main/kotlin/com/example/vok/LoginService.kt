package com.example.vok

import com.vaadin.flow.component.UI
import com.vaadin.flow.server.VaadinSession
import eu.vaadinonkotlin.vaadin10.Session
import java.io.Serializable

data class User(val name: String) : Serializable

object LoginService {
    fun login(user: User) {
        Session[User::class] = user
        UI.getCurrent().page.reload()
    }
    val currentUser: User? get() = Session[User::class]
    fun logout() {
        VaadinSession.getCurrent().close()
        UI.getCurrent().page.reload()
    }
}
