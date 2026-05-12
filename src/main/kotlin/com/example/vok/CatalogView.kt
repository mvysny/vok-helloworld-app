package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.ktormvaadin.dataProvider
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.Route
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
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

            horizontalLayout {
                val searchField = textField {
                    placeholder = "Search by name or SKU"
                    valueChangeMode = ValueChangeMode.LAZY
                    setWidth("20em")
                }
                val categoryField = comboBox<Category> {
                    placeholder = "Category"
                    setItems(Category.entries)
                    setWidth("12em")
                    isClearButtonVisible = true
                }

                fun applyFilters() {
                    dp.setFilter(productFilter(searchField.value, categoryField.value))
                }
                searchField.addValueChangeListener { applyFilters() }
                categoryField.addValueChangeListener { applyFilters() }
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

private fun productFilter(search: String, category: Category?): ColumnDeclaring<Boolean>? {
    val s = search.trim()
    val parts = listOfNotNull(
        if (s.isEmpty()) null else {
            val pattern = "%$s%"
            Products.name.ilike(pattern) or Products.sku.ilike(pattern)
        },
        category?.let { Products.category eq it },
    )
    return parts.reduceOrNull { a, b -> a and b }
}
