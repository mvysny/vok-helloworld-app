package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.data.binder.Binder

class ProductForm : KComposite() {

    val binder: Binder<Product> = beanValidationBinder()

    private val root = ui {
        formLayout {
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
        }
    }
}

fun (@VaadinDsl HasComponents).productForm(block: (@VaadinDsl ProductForm).() -> Unit = {}): ProductForm =
    init(ProductForm(), block)
