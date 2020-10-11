package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.HasComponents

class ArticleEditor : KComposite() {
    private val binder = beanValidationBinder<Article>()
    var article: Article? = null
        set(value) {
            field = value
            if (value != null) binder.readBean(value)
        }

    private val root = ui {
        verticalLayout {
            isMargin = false
            textField("Title") {
                bind(binder).bind(Article::title)
            }
            textArea("Text") {
                bind(binder).bind(Article::text)
            }
            button("Save Article") {
                onLeftClick {
                    val article = article!!
                    if (binder.validate().isOk && binder.writeBeanIfValid(article)) {
                        article.save()
                        ArticleView.navigateTo(article.id!!)
                    }
                }
            }
            routerLink(null, "Back", ArticlesView::class)
        }
    }
}

fun HasComponents.articleEditor(block: ArticleEditor.()->Unit = {}) = init(ArticleEditor(), block)
