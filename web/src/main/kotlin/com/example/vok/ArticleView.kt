package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.vokorm.getById
import com.vaadin.flow.component.*
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.*

@Route("article")
class ArticleView: VerticalLayout(), HasUrlParameter<Long> {
    private val editLink: RouterLink
    private lateinit var title: Text
    private lateinit var text: Text
    private val comments: CommentsComponent
    private val newComment: NewCommentForm
    init {
        div {
            strong("Title: ")
            this@ArticleView.title = text("")
        }
        div {
            strong("Text: ")
            this@ArticleView.text = text("")
        }
        comments = commentsComponent()
        newComment = newCommentForm {
            commentCreatedListener = { comments.refresh() }
        }
        editLink = routerLink(null, "Edit")
        routerLink(text = "Back", viewType = ArticlesView::class)
    }

    override fun setParameter(event: BeforeEvent, articleId: Long?) {
        val article = Article.getById(articleId!!)
        newComment.article = article
        comments.articleId = articleId
        title.text = article.title
        text.text = article.text
        editLink.setRoute(EditArticleView::class, articleId)
    }

    companion object {
        fun navigateTo(articleId: Long) = navigateToView(ArticleView::class, articleId)
    }
}
