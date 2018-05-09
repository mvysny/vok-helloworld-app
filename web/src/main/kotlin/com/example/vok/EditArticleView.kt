package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.github.vokorm.getById
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.*

@Route("edit-article")
class EditArticleView: VerticalLayout(), HasUrlParameter<Long> {
    private val binder = beanValidationBinder<Article>()
    private var article: Article? = null
    init {
        h1("Edit Article")
        textField("Title") {
            bind(binder).bind(Article::title)
        }
        textArea("Text") {
            bind(binder).bind(Article::text)
        }
        button("Save Article") {
            onLeftClick {
                val article = article!!
                if (binder.validate().isOk && binder.writeBeanIfValid(article)) {
                    article.save()
                    ArticleView.navigateTo(article.id!!)
                }
            }
        }
        routerLink(text = "Back", viewType = ArticlesView::class)
    }

    override fun setParameter(event: BeforeEvent, articleId: Long?) {
        edit(Article.getById(articleId!!))
    }

    private fun edit(article: Article) {
        this.article = article
        binder.readBean(article)
    }

    companion object {
        fun navigateTo(articleId: Long) = UI.getCurrent().navigate(EditArticleView::class.java, articleId)
    }
}
