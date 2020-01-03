package com.gildedrose

class RegularItem(
    name: String,
    sellIn: Int,
    quality: Int
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, item.quality)

    override fun calculateQuality(): Int {
        val newQuality = if (sellByDatePassed)
            quality - 2
        else
            quality - 1
        return validatedQuality(newQuality)
    }

    override fun copy(newName: String, newSellIn: Int, newQuality: Int) =
        RegularItem(newName, newSellIn, newQuality)
}
