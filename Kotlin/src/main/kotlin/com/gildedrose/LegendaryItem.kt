package com.gildedrose

class LegendaryItem(
    name: String,
    sellIn: Int,
    quality: Int
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, item.quality)

    override fun calculateSellByDate() = sellIn
    override fun calculateQuality() = quality

    override fun copy(newName: String, newSellIn: Int, newQuality: Int) =
        LegendaryItem(newName, newSellIn, newQuality)
}
