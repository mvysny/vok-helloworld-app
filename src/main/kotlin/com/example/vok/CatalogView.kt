package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.ktormvaadin.findAll
import com.vaadin.flow.router.Route

@Route("")
class CatalogView : KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); isPadding = true; isSpacing = true

            h2("BoltShop catalog")
            grid<Product>(Product::class) {
                setSizeFull()
                setItems(Products.findAll())
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
