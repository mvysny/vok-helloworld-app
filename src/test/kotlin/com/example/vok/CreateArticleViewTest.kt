package com.example.vok

import com.github.mvysny.kaributesting.v10._click
import com.github.mvysny.kaributesting.v10._expectOne
import com.github.mvysny.kaributesting.v10._get
import com.github.mvysny.kaributesting.v10._value
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.textfield.TextArea
import com.vaadin.flow.component.textfield.TextField
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.expect

class CreateArticleViewTest : AbstractAppTest() {
    @BeforeEach fun login() { loginAdmin() }

    @Test fun smoke() {
        navigateTo<CreateArticleView>()
        _expectOne<CreateArticleView>()
        _expectOne<ArticleEditor>()
    }

    @Test fun `successful create`() {
        navigateTo<CreateArticleView>()
        _get<TextField> { label = "Title" }._value = "Article Name"
        _get<TextArea> { label = "Text" }._value = "Article Name"
        _get<Button> { text = "Save Article" }._click()
        val articles = Article.findAll()
        expect(1) { articles.size }
    }
}
