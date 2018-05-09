package com.example.vok

import com.github.vokorm.*
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
}
