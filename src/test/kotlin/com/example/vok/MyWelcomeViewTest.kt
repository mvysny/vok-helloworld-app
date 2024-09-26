package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v10._expectOne

class MyWelcomeViewTest : DynaTest({
    usingApp()
    beforeEach { loginAdmin() }

    test("smoke") {
        // test that the app by default navigates to MyWelcomeView
        _expectOne<MyWelcomeView>()
    }
})