package com.example.vok

import com.github.vokorm.*

data class Article(
    override var id: Long? = null,

    var title: String? = null,

    var text: String? = null
) : Entity<Long> {
    companion object : Dao<Article>
}
