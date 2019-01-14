package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.vokorm.getById
import com.vaadin.flow.component.*
import com.vaadin.flow.router.*

@Route("article")
class ArticleView: KComposite(), HasUrlParameter<Long> {
    private lateinit var editLink: RouterLink
    private lateinit var title: Text
    private lateinit var text: Text
    private lateinit var comments: CommentsComponent
    private lateinit var newComment: NewCommentForm
    private val root = ui {
        verticalLayout {
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
