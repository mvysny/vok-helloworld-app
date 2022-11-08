package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import kotlin.test.expect

class CommentsComponentTest : DynaTest({
    usingApp()

    test("smoke") {
        val component = CommentsComponent()
        expect(0) { component.comments.componentCount }
    }
})
