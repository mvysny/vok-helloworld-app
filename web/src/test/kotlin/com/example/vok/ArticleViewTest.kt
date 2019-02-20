package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v10._click
import com.github.mvysny.kaributesting.v10._expectOne
import com.github.mvysny.kaributesting.v10._get
import com.github.mvysny.kaributesting.v10.expectView
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.router.RouterLink
import kotlin.test.expect

class ArticleViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        val article = Article(title = "Test Test", text = "Hello World!")
        article.save()
        ArticleView.navigateTo(article.id!!)
        _expectOne<ArticleView>()
        expect("Test Test") { _get<ArticleView>().title.text }
        expect("Hello World!") { _get<ArticleView>().text.text }
    }

    test("go back") {
        val article = Article(title = "Test Test", text = "Hello World!")
        article.save()
        ArticleView.navigateTo(article.id!!)
        _get<RouterLink> { text = "Back" } ._click()
        expectView<ArticlesView>()
    }
})
