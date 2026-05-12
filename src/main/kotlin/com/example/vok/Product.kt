package com.example.vok

import java.math.BigDecimal

enum class Category { Tools, Fasteners, Plumbing, Electrical, Paint, Garden }

enum class UnitOfMeasure { Each, Box, Meter, Kilogram }

data class Product(
    val sku: String,
    val name: String,
    val category: Category,
    val price: BigDecimal,
    val stock: Int,
    val unit: UnitOfMeasure
)
