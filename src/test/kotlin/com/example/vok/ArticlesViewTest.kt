package com.example.vok

import com.github.mvysny.kaributesting.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ArticlesViewTest : AbstractAppTest() {
    @BeforeEach fun login() { loginAdmin() }

    @Test fun smoke() {
        navigateTo<ArticlesView>()
        _expectOne<ArticlesView>()
        _expectOne<Grid<Article>>()
    }

    @Test fun `grid shows all articles`() {
        (0..9).forEach { Article(title = "Title $it", text = "Text $it").save() }
        navigateTo<ArticlesView>()
        val grid = _get<Grid<Article>>()
        grid.expectRows(10)
        grid.expectRow(0, Article.findAll()[0].id.toString(), "Title 0", "Text 0", "Show", "Edit", "Destroy")
    }

    @Test fun `delete article`() {
        Article(title = "Title", text = "Text").save()
        navigateTo<ArticlesView>()
        val grid = _get<Grid<Article>>()
        grid.expectRows(1)
        grid._clickRenderer(0, "destroy")
        // a confirm dialog should be displayed
        _expectOne<Dialog>()
        _get<Button> { text = "Yes" } ._click()
        expectList() { Article.findAll() }
    }
}
