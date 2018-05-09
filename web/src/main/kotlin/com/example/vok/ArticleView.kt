package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.github.vokorm.getById
import com.vaadin.flow.component.*
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.*

@Route("article")
class ArticleView: VerticalLayout(), HasUrlParameter<Long> {
    private lateinit var article: Article
    private val editLink: RouterLink
    private lateinit var title: Text
    private lateinit var text: Text
    private val comments: Div
    private val commentBinder = beanValidationBinder<Comment>()
    private lateinit var createComment: Button
    init {
        div {
            strong("Title: ")
            this@ArticleView.title = text("")
        }
        div {
            strong("Text: ")
            this@ArticleView.text = text("")
        }
        p("Comments")
        comments = div()
        p("Add a comment:")
        textField("Commenter:") {
            bind(commentBinder).bind(Comment::commenter)
        }
        textField("Body:") {
            bind(commentBinder).bind(Comment::body)
        }
        createComment = button("Create") {
            onLeftClick { createComment() }
        }
        editLink = routerLink(null, "Edit")
        routerLink(text = "Back", viewType = ArticlesView::class)
    }

    override fun setParameter(event: BeforeEvent, articleId: Long?) {
        article = Article.getById(articleId!!)
        title.text = article.title
        text.text = article.text
        editLink.setRoute(EditArticleView::class, articleId)
    }

    private fun createComment() {
        val comment = Comment()
        if (commentBinder.validate().isOk && commentBinder.writeBeanIfValid(comment)) {
            comment.article_id = article.id
            comment.save()
            refreshComments()
            commentBinder.readBean(Comment())  // this clears the comment fields
        }
    }
    private fun refreshComments() {
        comments.removeAll()
        article.comments.getAll().forEach { comment ->
            comments.html("<p><strong>Commenter:</strong>${comment.commenter}</p><p><strong>Comment:</strong>${comment.body}</p>")
        }
    }

    companion object {
        fun navigateTo(articleId: Long) = UI.getCurrent().navigate(ArticleView::class.java, articleId)
    }
}
