package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v10._click
import com.github.mvysny.kaributesting.v10._expectOne
import com.github.mvysny.kaributesting.v10._get
import com.github.mvysny.kaributesting.v10._value
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.textfield.TextArea
import com.vaadin.flow.component.textfield.TextField
import kotlin.test.expect

class CreateArticleViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        navigateTo<CreateArticleView>()
        _expectOne<CreateArticleView>()
        _expectOne<ArticleEditor>()
    }

    test("successful create") {
        navigateTo<CreateArticleView>()
        _get<TextField> { caption = "Title" }._value = "Article Name"
        _get<TextArea> { caption = "Text" }._value = "Article Name"
        _get<Button> { caption = "Save Article" }._click()
        val articles = Article.findAll()
        expect(1) { articles.size }
    }
})
