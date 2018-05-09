package com.example.vok

import com.github.vok.karibudsl.flow.getAll
import com.github.vokorm.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/articles")
class ArticleRest {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun get(@PathParam("id") id: Long): Article = Article.findById(id) ?: throw NotFoundException("No article with id $id")

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<Article> = Article.findAll()

    @GET
    @Path("/{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    fun getComments(@PathParam("id") id: Long): List<Comment> = get(id).comments.getAll()
}
