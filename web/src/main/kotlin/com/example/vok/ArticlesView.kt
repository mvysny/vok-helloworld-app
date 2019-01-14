package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.data.renderer.NativeButtonRenderer
import com.vaadin.flow.router.*
import eu.vaadinonkotlin.vaadin10.sql2o.dataProvider

@Route("articles")
class ArticlesView: KComposite(), AfterNavigationObserver {
    private lateinit var grid: Grid<Article>
    private val root = ui {
        verticalLayout {
            setSizeFull()
            h1("Listing Articles")
            routerLink(text = "New Article", viewType = CreateArticleView::class)
            grid = grid(dataProvider = Article.dataProvider) {
                isExpand = true; setSizeFull()
                addColumnFor(Article::id)
                addColumnFor(Article::title)
                addColumnFor(Article::text)
                addColumn(NativeButtonRenderer<Article>("Show", { ArticleView.navigateTo(it.id!!) }))
                addColumn(NativeButtonRenderer<Article>("Edit", { EditArticleView.navigateTo(it.id!!) }))
                addColumn(NativeButtonRenderer<Article>("Destroy", { article ->
                    confirmDialog {
                        article.delete()
                        this@grid.refresh()
                    }
                }))
            }
        }
    }

    override fun afterNavigation(event: AfterNavigationEvent) {
        grid.refresh()
    }
}
