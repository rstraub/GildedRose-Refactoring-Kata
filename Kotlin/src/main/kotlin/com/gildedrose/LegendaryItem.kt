package com.gildedrose

class LegendaryItem(
    name: String,
    sellIn: Int,
    quality: Quality
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, Quality(item.quality))

    override fun calculateSellByDate() = sellIn
    override fun calculateQuality(): Quality = quality

    override fun copy(newName: String, newSellIn: Int, newQuality: Quality) =
        LegendaryItem(newName, newSellIn, newQuality)
}
