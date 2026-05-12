package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.router.Route

@Route("")
class CatalogView : KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); isPadding = true; isSpacing = true

            h2("BoltShop catalog")
            grid<Product>(Product::class) {
                setSizeFull()
                setItems(sampleProducts)
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

private val sampleProducts: List<Product> = listOf(
    Product("HX-M6-40",      "Hex bolt M6×40mm, zinc-plated",    Category.Fasteners,  "0.35".toBigDecimal(), 120, UnitOfMeasure.Each),
    Product("HX-M8-50",      "Hex bolt M8×50mm, stainless",      Category.Fasteners,  "0.85".toBigDecimal(),   0, UnitOfMeasure.Each),
    Product("NT-M6-BOX100",  "Hex nut M6, zinc-plated, 100/box", Category.Fasteners,  "4.50".toBigDecimal(),  18, UnitOfMeasure.Box),
    Product("WD40-400",      "WD-40 lubricant spray 400ml",      Category.Tools,      "7.90".toBigDecimal(),  32, UnitOfMeasure.Each),
    Product("SD-FLAT-6",     "Screwdriver, flat blade 6mm",      Category.Tools,      "6.50".toBigDecimal(),  12, UnitOfMeasure.Each),
    Product("PIPE-CU-22",    "Copper pipe Ø22mm",                Category.Plumbing,  "12.40".toBigDecimal(),  45, UnitOfMeasure.Meter),
    Product("CABLE-3G15",    "Power cable 3G1.5mm²",             Category.Electrical, "1.80".toBigDecimal(), 200, UnitOfMeasure.Meter),
    Product("PAINT-WHT-1L",  "Interior paint, white matte 1L",   Category.Paint,     "14.90".toBigDecimal(),   8, UnitOfMeasure.Each),
    Product("PAINT-WHT-10L", "Interior paint, white matte 10L",  Category.Paint,     "89.00".toBigDecimal(),   3, UnitOfMeasure.Each),
    Product("SAND-CONCRETE", "Concrete sand",                    Category.Garden,     "0.45".toBigDecimal(), 800, UnitOfMeasure.Kilogram),
)
