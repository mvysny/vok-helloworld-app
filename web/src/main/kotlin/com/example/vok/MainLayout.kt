package com.example.vok

import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.RouterLayout
import eu.vaadinonkotlin.vaadin10.Session
import eu.vaadinonkotlin.vaadin10.VokSecurity

/**
 * The main layout:
 * * provides application frame around all views
 * * handles redirect to the [LoginView] if no user is logged in.
 */
@Viewport(Viewport.DEVICE_DIMENSIONS)
class MainLayout : Div(), RouterLayout, BeforeEnterObserver {
    init {
        setSizeFull()
    }

    override fun beforeEnter(event: BeforeEnterEvent) {
        if (event.navigationTarget != LoginView::class.java && !Session.loginService.isLoggedIn) {
            event.rerouteTo(LoginView::class.java)
        }
    }
}
