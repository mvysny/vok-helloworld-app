package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v10._expectOne
import com.github.mvysny.kaributools.navigateTo

class WelcomeViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        navigateTo<WelcomeView>()
        _expectOne<WelcomeView>()
    }
})
