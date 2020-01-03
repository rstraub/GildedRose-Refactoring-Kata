package com.gildedrose

class LegendaryItem(
    name: String,
    sellIn: Int,
    quality: Int
) : StoreItem(name, sellIn, quality) {
    override fun withNewSellByDate() = copy()
    override fun calculateQuality(): Int = quality

    override fun copy(newName: String, newSellIn: Int, newQuality: Int) =
        LegendaryItem(newName, newSellIn, newQuality)
}
