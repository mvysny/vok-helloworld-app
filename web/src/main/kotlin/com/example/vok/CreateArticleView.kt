package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.router.Route

@Route("create-article")
class CreateArticleView: KComposite() {
    private lateinit var editor: ArticleEditor
    private val root = ui {
        verticalLayout {
            h1("New Article")
            editor = articleEditor {
                article = Article()
            }
        }
    }
}
