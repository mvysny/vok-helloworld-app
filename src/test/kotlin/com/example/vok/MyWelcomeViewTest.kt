package com.example.vok

import com.github.mvysny.kaributesting.v10._expectOne
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MyWelcomeViewTest : AbstractAppTest() {
    @BeforeEach fun login() { loginAdmin() }

    @Test fun smoke() {
        // test that the app by default navigates to MyWelcomeView
        _expectOne<MyWelcomeView>()
    }
}