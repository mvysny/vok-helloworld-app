package com.example.vok

import com.github.mvysny.dynatest.DynaNodeGroup
import com.github.mvysny.kaributesting.v10.*
import com.github.vokorm.deleteAll
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.textfield.PasswordField
import com.vaadin.flow.component.textfield.TextField

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
