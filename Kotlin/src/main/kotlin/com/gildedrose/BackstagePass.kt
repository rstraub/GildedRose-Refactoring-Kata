package com.gildedrose

class BackstagePass(
    name: String,
    sellIn: Int,
    quality: Quality
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, Quality(item.quality))

    override fun calculateQuality() = when {
        sellByDatePassed -> Quality(0)
        sellIn <= 5 -> quality + 3
        sellIn <= 10 -> quality + 2
        else -> quality + 1
    }

    override fun copy(
        newName: String,
        newSellIn: Int,
        newQuality: Quality
    ) = BackstagePass(newName, newSellIn, newQuality)
}
