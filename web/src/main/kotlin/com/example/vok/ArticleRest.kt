package com.example.vok

import com.github.mvysny.karibudsl.v10.getAll
import com.github.vokorm.*
import io.javalin.Javalin
import io.javalin.NotFoundResponse

fun Javalin.articleRest() {
    get("/rest/articles/:id") { ctx ->
        val id = ctx.pathParam("id").toLong()
        ctx.json(Article.findById(id) ?: throw NotFoundResponse("No article with id $id"))
    }
    get("/rest/articles") { ctx -> ctx.json(Article.findAll()) }
    get("/rest/articles/:id/comments") { ctx ->
        val id = ctx.pathParam("id").toLong()
        val article = Article.findById(id) ?: throw NotFoundResponse("No article with id $id")
        ctx.json(article.comments.getAll())
    }
}
