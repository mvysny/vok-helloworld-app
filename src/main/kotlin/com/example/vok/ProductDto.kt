package com.example.vok

import java.math.BigDecimal

/**
 * Wire representation of a [Product] for the REST API. A plain Kotlin data
 * class so Gson can deserialize JSON requests into it; convert in either
 * direction with [Product.toDto] / [ProductDto.toEntity].
 */
data class ProductDto(
    val id: Long? = null,
    val sku: String? = null,
    val name: String? = null,
    val category: Category? = null,
    val price: BigDecimal? = null,
    val stock: Int? = null,
    val unit: UnitOfMeasure? = null,
)

fun Product.toDto(): ProductDto = ProductDto(id, sku, name, category, price, stock, unit)

fun ProductDto.toEntity(): Product = Product {
    sku = this@toEntity.sku
    name = this@toEntity.name
    category = this@toEntity.category
    price = this@toEntity.price
    stock = this@toEntity.stock
    unit = this@toEntity.unit
}
