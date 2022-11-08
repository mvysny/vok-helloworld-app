package com.example.vok

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.div
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.RouterLayout
import eu.vaadinonkotlin.vaadin.Session

/**
 * The main layout:
 * * provides application frame around all views
 * * handles redirect to the [LoginView] if no user is logged in.
 */
@Viewport(Viewport.DEVICE_DIMENSIONS)
class MainLayout : KComposite(), RouterLayout, BeforeEnterObserver {
    private val root = ui {
        div {
            setSizeFull()
        }
    }

    /**
     * Invoked before a view is shown. We will perform security checks here - if
     * no user is logged in, we'll simply redirect to the LoginView.
     */
    override fun beforeEnter(event: BeforeEnterEvent) {
        if (event.navigationTarget != LoginView::class.java && !Session.loginService.isLoggedIn) {
            event.rerouteTo(LoginView::class.java)
        }
    }
}
