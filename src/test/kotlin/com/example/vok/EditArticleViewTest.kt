package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v10._expectOne
import com.github.mvysny.kaributesting.v10._get
import com.vaadin.flow.component.textfield.TextArea
import com.vaadin.flow.component.textfield.TextField
import kotlin.test.expect

class EditArticleViewTest : DynaTest({
    usingApp()
    beforeEach { loginAdmin() }

    test("smoke") {
        val article = Article(title = "Test Test", text = "Hello World!")
        article.save()
        EditArticleView.navigateTo(article.id!!)
        _expectOne<EditArticleView>()
        _expectOne<ArticleEditor>()
        expect("Test Test") { _get<TextField> { label = "Title" } .value }
        expect("Hello World!") { _get<TextArea> { label = "Text" } .value }
    }
})