package com.example.vok

import com.github.mvysny.dynatest.DynaNodeGroup
import com.github.mvysny.kaributesting.v10.*
import com.vaadin.flow.component.login.LoginForm

fun DynaNodeGroup.usingApp() {
    beforeGroup { Bootstrap().contextInitialized(null) }
    afterGroup { Bootstrap().contextDestroyed(null) }
    fun cleanDatabase() {
        Comment.deleteAll()
        Article.deleteAll()
    }
    beforeEach { cleanDatabase() }
    afterEach { cleanDatabase() }

    beforeEach { MockVaadin.setup(Routes().autoDiscoverViews("com.example.vok")) }
    afterEach { MockVaadin.tearDown() }
}

fun login() {
    _get<LoginForm>()._login("admin", "admin")
}
