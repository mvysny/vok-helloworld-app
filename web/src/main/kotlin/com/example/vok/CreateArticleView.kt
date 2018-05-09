package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("create-article")
class CreateArticleView: VerticalLayout() {
    private val binder = beanValidationBinder<Article>()
    init {
        h1("New Article")
        textField("Title") {
            bind(binder).bind(Article::title)
        }
        textArea("Text") {
            bind(binder).bind(Article::text)
        }
        button("Save Article") {
            onLeftClick {
                val article = Article()
                if (binder.writeBeanIfValid(article)) {
                    article.save()
                    ArticleView.navigateTo(article.id!!)
                }
            }
        }
        routerLink(text = "Back", viewType = ArticlesView::class.java)
    }
}
