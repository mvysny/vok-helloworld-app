package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.github.vokorm.getById
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.*

@Route("edit-article")
class EditArticleView: VerticalLayout(), HasUrlParameter<Long> {
    private val editor: ArticleEditor
    init {
        h1("Edit Article")
        editor = articleEditor()
    }

    override fun setParameter(event: BeforeEvent, articleId: Long?) {
        editor.article = Article.getById(articleId!!)
    }

    companion object {
        fun navigateTo(articleId: Long) = UI.getCurrent().navigate(EditArticleView::class.java, articleId)
    }
}
