package com.example.vok

import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.karibudsl.flow.*
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.*

@Route("articles")
class ArticlesView: VerticalLayout(), AfterNavigationObserver {
    private val grid: Grid<Article>
    init {
        setSizeFull()
        h1("Listing Articles")
        grid = grid(dataProvider = Article.dataProvider) {
            isExpand = true; setSizeFull()
            addColumnFor(Article::id)
            addColumnFor(Article::title)
            addColumnFor(Article::text)
        }
    }

    override fun afterNavigation(event: AfterNavigationEvent) {
        grid.refresh()
    }
}
