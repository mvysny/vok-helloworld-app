package com.example.vok

import com.github.mvysny.ktormvaadin.ActiveEntity
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import org.ktorm.entity.Entity
import org.ktorm.schema.Column
import org.ktorm.schema.Table
import org.ktorm.schema.decimal
import org.ktorm.schema.enum
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import java.math.BigDecimal

enum class Category { Tools, Fasteners, Plumbing, Electrical, Paint, Garden }

enum class UnitOfMeasure { Each, Box, Meter, Kilogram }

interface Product : ActiveEntity<Product> {
    var id: Long?

    @get:NotNull
    @get:Size(min = 1, max = 40)
    @get:Pattern(
        regexp = "[A-Z0-9-]+",
        message = "SKU may only contain uppercase letters, digits and hyphens"
    )
    var sku: String?

    @get:NotNull
    @get:Size(min = 1, max = 200)
    var name: String?

    @get:NotNull
    var category: Category?

    @get:NotNull
    @get:Positive(message = "Price must be greater than zero")
    var price: BigDecimal?

    @get:NotNull
    @get:PositiveOrZero(message = "Stock cannot be negative")
    var stock: Int?

    @get:NotNull
    var unit: UnitOfMeasure?

    override val table: Table<Product> get() = Products

    companion object : Entity.Factory<Product>()
}

object Products : Table<Product>("Product") {
    val id: Column<Long> = long("id").primaryKey().bindTo { it.id }
    val sku: Column<String> = varchar("sku").bindTo { it.sku }
    val name: Column<String> = varchar("name").bindTo { it.name }
    val category: Column<Category> = enum<Category>("category").bindTo { it.category }
    val price: Column<BigDecimal> = decimal("price").bindTo { it.price }
    val stock: Column<Int> = int("stock").bindTo { it.stock }
    val unit: Column<UnitOfMeasure> = enum<UnitOfMeasure>("unit").bindTo { it.unit }
}
