package com.example.vok

import com.github.vok.karibudsl.flow.*
import com.github.vokorm.getById
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class CommentsComponent : VerticalLayout() {
    var articleId: Long = 0L
        set(value) { field = value; refresh() }

    private val comments: VerticalLayout
    init {
        isMargin = false
        p("Comments")
        comments = verticalLayout()
    }

    fun refresh() {
        comments.removeAll()
        Article.getById(articleId).comments.getAll().forEach { comment ->
            comments.div {
                html("<p><strong>Commenter:</strong>${comment.commenter}</p><p><strong>Comment:</strong>${comment.body}</p>")
            }
            comments.button("Delete comment") {
                themes.add("tertiary small")
                onLeftClick { comment.delete(); refresh() }
            }
        }
    }
}
// the extension function which will allow us to use CommentsComponent inside a DSL
fun HasComponents.commentsComponent(block: CommentsComponent.()->Unit = {}) = init(CommentsComponent(), block)
