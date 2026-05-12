package com.example.vok

import com.github.mvysny.kaributesting.v10.*
import com.github.mvysny.ktormvaadin.findAll
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.checkbox.Checkbox
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.textfield.BigDecimalField
import com.vaadin.flow.component.textfield.IntegerField
import com.vaadin.flow.component.textfield.TextField
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CatalogViewTest : AbstractAppTest() {

    @Test
    fun `view shows seed products`() {
        _get<Grid<Product>>().expectRows(10)
    }

    @Test
    fun `search by SKU filters Grid`() {
        _get<TextField> { placeholder = "Search by name or SKU" }._value = "PAINT"
        _get<Grid<Product>>().expectRows(2)
    }

    @Test
    fun `search by name filters Grid`() {
        _get<TextField> { placeholder = "Search by name or SKU" }._value = "bolt"
        _get<Grid<Product>>().expectRows(2)
    }

    @Test
    fun `category filter narrows results`() {
        _get<ComboBox<Category>> { placeholder = "Category" }._value = Category.Fasteners
        _get<Grid<Product>>().expectRows(3)
    }

    @Test
    fun `low stock checkbox narrows results`() {
        _get<Checkbox> { label = "Low stock only" }._value = true
        _get<Grid<Product>>().expectRows(3)
    }

    @Test
    fun `selecting a row populates the side panel form`() {
        val grid = _get<Grid<Product>>()
        val product = grid._get(0)
        grid._selectRow(0)
        assertEquals(product.sku, _get<TextField> { label = "SKU" }._value)
        assertEquals(product.name, _get<TextField> { label = "Name" }._value)
    }

    @Test
    fun `save persists changes`() {
        val grid = _get<Grid<Product>>()
        grid._selectRow(0)
        val original = grid._get(0)

        _get<BigDecimalField> { label = "Price" }._value = "99.99".toBigDecimal()
        _get<Button> { text = "Save" }._click()

        expectNotifications("Saved ${original.name}")
        val updated = Products.findAll().first { it.sku == original.sku }
        assertEquals("99.99".toBigDecimal(), updated.price)
    }

    @Test
    fun `delete removes the row`() {
        val grid = _get<Grid<Product>>()
        grid._selectRow(0)
        val original = grid._get(0)

        _get<Button> { text = "Delete" }._click()

        expectNotifications("Deleted ${original.name}")
        grid.expectRows(9)
    }

    @Test
    fun `add dialog creates a new product`() {
        _get<Button> { text = "+ Add product" }._click()
        val dialog = _get<Dialog>()

        dialog._get<TextField> { label = "SKU" }._value = "NEW-PRODUCT-1"
        dialog._get<TextField> { label = "Name" }._value = "New product"
        dialog._get<ComboBox<Category>> { label = "Category" }._value = Category.Tools
        dialog._get<BigDecimalField> { label = "Price" }._value = "5.50".toBigDecimal()
        dialog._get<IntegerField> { label = "Stock" }._value = 100
        dialog._get<ComboBox<UnitOfMeasure>> { label = "Unit" }._value = UnitOfMeasure.Each
        dialog._get<Button> { text = "Create" }._click()

        expectNotifications("Created New product")
        _get<Grid<Product>>().expectRows(11)
    }

    @Test
    fun `empty add dialog is rejected by validation`() {
        _get<Button> { text = "+ Add product" }._click()
        val dialog = _get<Dialog>()
        dialog._get<Button> { text = "Create" }._click()

        _expectOne<Dialog>()
        _get<Grid<Product>>().expectRows(10)
    }
}
