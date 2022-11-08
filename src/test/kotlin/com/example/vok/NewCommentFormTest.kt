package com.example.vok

import com.github.mvysny.dynatest.DynaTest

class NewCommentFormTest : DynaTest({
    usingApp()

    test("smoke") {
        NewCommentForm()
    }
})