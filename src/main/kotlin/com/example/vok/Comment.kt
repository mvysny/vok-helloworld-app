package com.example.vok

import com.github.vokorm.*
import com.gitlab.mvysny.jdbiorm.Dao
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class Comment(
    override var id: Long? = null,

    var article_id: Long? = null,

    @field:NotNull
    @field:Length(min = 3)
    var commenter: String? = null,

    @field:NotNull
    @field:Length(min = 3)
    var body: String? = null
) : KEntity<Long> {
    companion object : Dao<Comment, Long>(Comment::class.java)

    val article: Article? get() = if (article_id == null) null else Article.findById(article_id!!)
}
