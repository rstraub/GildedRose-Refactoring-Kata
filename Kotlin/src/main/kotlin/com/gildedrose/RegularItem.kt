package com.gildedrose

class RegularItem(
    name: String,
    sellIn: Int,
    quality: Quality
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, Quality(item.quality))

    override fun calculateQuality() =
        if (sellByDatePassed)
            quality - 2
        else
            quality - 1

    override fun copy(newName: String, newSellIn: Int, newQuality: Quality) =
        RegularItem(newName, newSellIn, newQuality)
}
