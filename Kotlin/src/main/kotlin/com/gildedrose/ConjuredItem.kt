package com.gildedrose

class ConjuredItem(
    name: String,
    sellIn: Int,
    quality: Quality
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, Quality(item.quality))

    override fun calculateQuality() =
        if (sellByDatePassed)
            quality - 4
        else
            quality - 2

    override fun copy(newName: String, newSellIn: Int, newQuality: Quality) =
        ConjuredItem(newName, newSellIn, newQuality)
}
