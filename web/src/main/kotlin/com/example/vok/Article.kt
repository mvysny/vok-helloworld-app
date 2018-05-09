package com.example.vok

import com.github.vok.framework.sql2o.vaadin.*
import com.github.vokorm.*
import com.vaadin.flow.data.provider.DataProvider
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class Article(
    override var id: Long? = null,

    @field:NotNull
    @field:Length(min = 5)
    var title: String? = null,

    @field:NotNull
    @field:Length(min = 2)
    var text: String? = null
) : Entity<Long> {
    companion object : Dao<Article>

    val comments: VokDataProvider<Comment> get() = Comment.dataProvider.withFilter { Comment::article_id eq id }

    override fun delete() = db {
        Comment.deleteBy { Comment::article_id eq id }
        super.delete()
    }
}
