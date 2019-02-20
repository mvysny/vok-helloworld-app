package com.example.vok

import com.github.mvysny.dynatest.DynaNodeGroup
import com.github.mvysny.kaributesting.v10.*
import com.github.vokorm.deleteAll
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.textfield.PasswordField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.RouterLink
import com.vaadin.flow.server.RouteRegistry
import kotlin.test.expect

fun DynaNodeGroup.usingApp() {
    beforeGroup { Bootstrap().contextInitialized(null) }
    afterGroup { Bootstrap().contextDestroyed(null) }
    fun cleanDatabase() {
        Comment.deleteAll()
        Article.deleteAll()
    }
    beforeEach { cleanDatabase() }
    afterEach { cleanDatabase() }

    beforeEach { MockVaadin.setup(Routes().autoDiscoverViews("com.example.vok"), uiFactory = { MyUI() }) }
    afterEach { MockVaadin.tearDown() }
}

fun login() {
    _get<TextField> { caption = "Username" }._value = "admin"
    _get<PasswordField> { caption = "Password" }._value = "admin"
    _get<Button> { caption = "Login" }._click()
}

// @todo mavi remove when upgraded to KT 1.1.3
/**
 * Returns the browser's current path. Returns null if there is no current UI.
 */
val currentPath: String? get() {
    return UI.getCurrent()?.internals?.activeViewLocation?.pathWithQueryParameters
}

/**
 * Returns the current view
 */
val currentView: Class<out Component>? get() {
    val path = (currentPath ?: return null).trim('/')
    val registry: RouteRegistry = UI.getCurrent().router.registry
    val segments = path.split('/')
    for (prefix in segments.size downTo 1) {
        val p = segments.subList(0, prefix).joinToString("/")
        val s = segments.subList(prefix, segments.size)
        val clazz = registry.getNavigationTarget(p, s).orElse(null)
        if (clazz != null) {
            return clazz
        }
    }
    return null
}

/**
 * Expects that given [view] is the currently displayed view.
 */
fun <V: Component> expectView(view: Class<V>) {
    @Suppress("UNCHECKED_CAST")
    expect(view) { currentView }
}

/**
 * Expects that given view is the currently displayed view.
 */
inline fun <reified V: Component> expectView() = expectView(V::class.java)

fun RouterLink._click() {
    click()
}
