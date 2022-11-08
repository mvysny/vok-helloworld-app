package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.router.*

@Route("edit-article", layout = MainLayout::class)
class EditArticleView: KComposite(), HasUrlParameter<Long> {
    private lateinit var editor: ArticleEditor
    private val root = ui {
        verticalLayout {
            h1("Edit Article")
            editor = articleEditor()
        }
    }

    override fun setParameter(event: BeforeEvent, articleId: Long?) {
        editor.article = Article.getById(articleId!!)
    }

    companion object {
        fun navigateTo(articleId: Long) = navigateTo(EditArticleView::class, articleId)
    }
}
