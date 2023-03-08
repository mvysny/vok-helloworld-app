package com.example.vok

import com.github.mvysny.vokdataloader.DataLoader
import com.github.mvysny.vokdataloader.withFilter
import com.github.vokorm.*
import com.github.vokorm.dataloader.dataLoader
import com.gitlab.mvysny.jdbiorm.Dao
import org.hibernate.validator.constraints.Length
import jakarta.validation.constraints.NotNull

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

    val comments: DataLoader<Comment> get() = Comment.dataLoader.withFilter { Comment::article_id eq id }

    override fun delete() = db {
        Comment.deleteBy { Comment::article_id eq id }
        super.delete()
    }
}
