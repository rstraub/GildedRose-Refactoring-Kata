package com.gildedrose

class LegendaryItem(
    name: String,
    sellIn: Int,
    quality: Int
) : StoreItem(name, sellIn, quality) {
    override fun withNewSellByDate() = LegendaryItem(name, sellIn, quality)
    override fun withNewQuality(): StoreItem = LegendaryItem(name, sellIn, quality)
}
