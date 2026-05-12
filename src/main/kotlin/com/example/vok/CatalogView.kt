package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.ktormvaadin.dataProvider
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.Route
import org.ktorm.dsl.or
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.support.postgresql.ilike

@Route("")
class CatalogView : KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); isPadding = true; isSpacing = true

            h2("BoltShop catalog")

            val dp = Products.dataProvider

            textField {
                placeholder = "Search by name or SKU"
                valueChangeMode = ValueChangeMode.LAZY
                setWidth("20em")
                addValueChangeListener { e ->
                    dp.setFilter(productFilter(e.value))
                }
            }

            grid<Product>(Product::class, dp) {
                setSizeFull()
                columnFor(Product::sku) { setHeader("SKU") }
                columnFor(Product::name) { setHeader("Name"); flexGrow = 1 }
                columnFor(Product::category) { setHeader("Category") }
                columnFor(Product::price) { setHeader("Price") }
                columnFor(Product::stock) { setHeader("Stock") }
                columnFor(Product::unit) { setHeader("Unit") }
            }
        }
    }
}

private fun productFilter(query: String): ColumnDeclaring<Boolean>? {
    val q = query.trim()
    if (q.isEmpty()) return null
    val pattern = "%$q%"
    return Products.name.ilike(pattern) or Products.sku.ilike(pattern)
}
