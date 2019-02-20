package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.vokorm.getById
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class CommentsComponent : KComposite() {
    var articleId: Long = 0L
        set(value) { field = value; refresh() }

    internal lateinit var comments: VerticalLayout
    private val root = ui {
        verticalLayout {
            isMargin = false
            p("Comments")
            comments = verticalLayout()
        }
    }

    fun refresh() {
        comments.removeAll()
        Article.getById(articleId).comments.getAll().forEach { comment ->
            comments.apply {
                div {
                    html("<p><strong>Commenter:</strong>${comment.commenter}</p><p><strong>Comment:</strong>${comment.body}</p>")
                }
                button("Delete comment") {
                    addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SMALL)
                    onLeftClick { comment.delete(); refresh() }
                }
            }
        }
    }
}
// the extension function which will allow us to use CommentsComponent inside a DSL
fun HasComponents.commentsComponent(block: CommentsComponent.()->Unit = {}) = init(CommentsComponent(), block)
