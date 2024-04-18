package com.example.vok

import com.github.vokorm.KEntity
import com.github.vokorm.buildCondition
import com.github.vokorm.db
import com.github.vokorm.deleteBy
import com.gitlab.mvysny.jdbiorm.Dao
import com.gitlab.mvysny.jdbiorm.condition.Condition
import com.vaadin.flow.data.provider.DataProvider
import eu.vaadinonkotlin.vaadin.vokdb.dataProvider
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

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

    val comments: DataProvider<Comment, Condition> get() = Comment.dataProvider.apply { filter = buildCondition<Comment> { Comment::article_id eq id } }

    override fun delete() = db {
        Comment.deleteBy { Comment::article_id eq id }
        super.delete()
    }
}
