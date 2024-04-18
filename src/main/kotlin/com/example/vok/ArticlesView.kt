package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.refresh
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.data.renderer.NativeButtonRenderer
import com.vaadin.flow.router.*
import eu.vaadinonkotlin.vaadin.vokdb.dataProvider

@Route("articles", layout = MainLayout::class)
class ArticlesView: KComposite(), AfterNavigationObserver {
    private lateinit var grid: Grid<Article>
    private val root = ui {
        verticalLayout {
            setSizeFull()
            h1("Listing Articles")
            routerLink(text = "New Article", viewType = CreateArticleView::class)
            grid = grid(Article.dataProvider) {
                isExpand = true; setSizeFull()

                columnFor(Article::id) {}
                columnFor(Article::title) {}
                columnFor(Article::text) {}
                addColumn(NativeButtonRenderer<Article>("Show", { ArticleView.navigateTo(it.id!!) }))
                addColumn(NativeButtonRenderer<Article>("Edit", { EditArticleView.navigateTo(it.id!!) }))
                addColumn(NativeButtonRenderer<Article>("Destroy", { article ->
                    confirmDialog {
                        article.delete()
                        this@grid.refresh()
                    }
                })).key = "destroy"
            }
        }
    }

    override fun afterNavigation(event: AfterNavigationEvent) {
        grid.refresh()
    }
}
