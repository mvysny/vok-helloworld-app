package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("create-article")
class CreateArticleView: VerticalLayout() {
    private val editor: ArticleEditor
    init {
        h1("New Article")
        editor = articleEditor {
            article = Article()
        }
    }
}
