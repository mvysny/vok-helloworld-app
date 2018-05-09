package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.github.vokorm.getById
import com.vaadin.flow.component.*
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.*

@Route("article")
class ArticleView: VerticalLayout(), HasUrlParameter<Long> {
    private lateinit var title: Text
    private lateinit var text: Text
    init {
        div {
            strong("Title: ")
            this@ArticleView.title = text("")
        }
        div {
            strong("Text: ")
            this@ArticleView.text = text("")
        }
        routerLink(text = "Back", viewType = ArticlesView::class.java)
    }

    override fun setParameter(event: BeforeEvent, articleId: Long?) {
        val article = Article.getById(articleId!!)
        title.text = article.title
        text.text = article.text
    }

    companion object {
        fun navigateTo(articleId: Long) = UI.getCurrent().navigate(ArticleView::class.java, articleId)
    }
}
