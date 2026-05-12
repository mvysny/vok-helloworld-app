package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.setPrimary
import com.github.mvysny.ktormvaadin.dataProvider
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.Route
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.or
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.support.postgresql.ilike

@Route("")
class CatalogView : KComposite() {

    private val dp = Products.dataProvider
    private val binder = Binder<Product>(Product::class.java)
    private var selected: Product? = null
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button

    private val root = ui {
        verticalLayout {
            setSizeFull(); isPadding = true; isSpacing = true

            h2("BoltShop catalog")

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

            horizontalLayout {
                setSizeFull(); isSpacing = true

                grid<Product>(Product::class, dp) {
                    flexGrow = 1.0
                    setSizeFull()
                    columnFor(Product::sku) { setHeader("SKU") }
                    columnFor(Product::name) { setHeader("Name"); flexGrow = 1 }
                    columnFor(Product::category) { setHeader("Category") }
                    columnFor(Product::price) { setHeader("Price") }
                    columnFor(Product::stock) { setHeader("Stock") }
                    columnFor(Product::unit) { setHeader("Unit") }

                    asSingleSelect().addValueChangeListener { e -> showSelection(e.value) }
                }

                verticalLayout {
                    setWidth("28em"); isPadding = true; isSpacing = true

                    h3("Product details")

                    textField("SKU") { bind(binder).bind(Product::sku) }
                    textField("Name") { bind(binder).bind(Product::name) }
                    comboBox<Category>("Category") {
                        setItems(Category.entries)
                        bind(binder).bind(Product::category)
                    }
                    bigDecimalField("Price") { bind(binder).bind(Product::price) }
                    integerField("Stock") { bind(binder).bind(Product::stock) }
                    comboBox<UnitOfMeasure>("Unit") {
                        setItems(UnitOfMeasure.entries)
                        bind(binder).bind(Product::unit)
                    }

                    horizontalLayout {
                        saveButton = button("Save") {
                            setPrimary()
                            onClick { onSave() }
                        }
                        deleteButton = button("Delete") {
                            onClick { onDelete() }
                        }
                    }
                }
            }
        }
    }

    init {
        showSelection(null)
    }

    private fun showSelection(product: Product?) {
        selected = product
        binder.readBean(product)
        saveButton.isEnabled = product != null
        deleteButton.isEnabled = product != null
    }

    private fun onSave() {
        val product = selected ?: return
        if (!binder.writeBeanIfValid(product)) return
        product.save()
        dp.refreshAll()
        Notification.show("Saved ${product.name}")
    }

    private fun onDelete() {
        val product = selected ?: return
        product.delete()
        dp.refreshAll()
        showSelection(null)
        Notification.show("Deleted ${product.name}")
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
