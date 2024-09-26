package com.example.vok

import com.github.mvysny.kaributesting.v10._expectOne
import com.github.mvysny.kaributools.navigateTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WelcomeViewTest : AbstractAppTest() {
    @BeforeEach fun login() { loginAdmin() }

    @Test fun smoke() {
        navigateTo<WelcomeView>()
        _expectOne<WelcomeView>()
    }
}
