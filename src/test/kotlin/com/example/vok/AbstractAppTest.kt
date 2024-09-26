package com.example.vok

import com.github.mvysny.kaributesting.v10.*
import com.vaadin.flow.component.login.LoginForm
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

abstract class AbstractAppTest {
    companion object {
        @BeforeAll @JvmStatic fun initApp() { Bootstrap().contextInitialized(null) }
        @BeforeAll @JvmStatic fun stopApp() { Bootstrap().contextDestroyed(null) }
    }

    @BeforeEach @AfterEach
    fun cleanDatabase() {
        Comment.deleteAll()
        Article.deleteAll()
    }

    @BeforeEach fun fakeVaadin() { MockVaadin.setup(Routes().autoDiscoverViews("com.example.vok")) }
    @AfterEach fun tearDownVaadin() { MockVaadin.tearDown() }
}

fun loginAdmin() {
    _get<LoginForm>()._login("admin", "admin")
}
