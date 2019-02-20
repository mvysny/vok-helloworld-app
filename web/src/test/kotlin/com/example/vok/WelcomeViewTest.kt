package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.karibudsl.v10.navigateToView
import com.github.mvysny.kaributesting.v10._expectOne

class WelcomeViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        navigateToView<WelcomeView>()
        _expectOne<WelcomeView>()
    }
})