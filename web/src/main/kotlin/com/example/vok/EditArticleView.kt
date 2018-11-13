package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.vokorm.getById
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
        fun navigateTo(articleId: Long) = navigateToView(EditArticleView::class, articleId)
    }
}
