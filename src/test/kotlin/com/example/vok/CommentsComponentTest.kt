package com.example.vok

import org.junit.jupiter.api.Test
import kotlin.test.expect

class CommentsComponentTest : AbstractAppTest() {
    @Test fun smoke() {
        val component = CommentsComponent()
        expect(0) { component.comments.componentCount }
    }
}
