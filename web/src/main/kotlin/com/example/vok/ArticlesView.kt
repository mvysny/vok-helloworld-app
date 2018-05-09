package com.example.vok

import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.karibudsl.flow.*
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.data.renderer.NativeButtonRenderer
import com.vaadin.flow.router.*

@Route("articles")
class ArticlesView: VerticalLayout(), AfterNavigationObserver {
    private val grid: Grid<Article>
    init {
        setSizeFull()
        h1("Listing Articles")
        routerLink(text = "New Article", viewType = CreateArticleView::class.java)
        grid = grid(dataProvider = Article.dataProvider) {
            isExpand = true; setSizeFull()
            addColumnFor(Article::id)
            addColumnFor(Article::title)
            addColumnFor(Article::text)
            addColumn(NativeButtonRenderer<Article>("Show", { ArticleView.navigateTo(it.id!!) }))
            addColumn(NativeButtonRenderer<Article>("Edit", { EditArticleView.navigateTo(it.id!!) }))
        }
    }

    override fun afterNavigation(event: AfterNavigationEvent) {
        grid.refresh()
    }
}
