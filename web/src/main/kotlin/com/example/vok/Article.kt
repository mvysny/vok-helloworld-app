package com.example.vok

import com.github.vokorm.*
import com.gitlab.mvysny.jdbiorm.Dao
import eu.vaadinonkotlin.vaadin10.*
import eu.vaadinonkotlin.vaadin10.vokdb.dataProvider
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
) : KEntity<Long> {
    companion object : Dao<Article, Long>(Article::class.java)

    val comments: VokDataProvider<Comment> get() = Comment.dataProvider.withFilter { Comment::article_id eq id }

    override fun delete() = db {
        Comment.deleteBy { Comment::article_id eq id }
        super.delete()
    }
}
