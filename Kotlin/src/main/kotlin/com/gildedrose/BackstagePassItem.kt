package com.gildedrose

import com.gildedrose.Quality.Companion.MIN_QUALITY

class BackstagePassItem(
    name: String,
    sellIn: Int,
    quality: Quality
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, Quality(item.quality))

    override fun calculateQuality() = when {
        sellByDatePassed -> MIN_QUALITY
        sellIn <= 5 -> quality + 3
        sellIn <= 10 -> quality + 2
        else -> quality + 1
    }

    override fun copy(
        newName: String,
        newSellIn: Int,
        newQuality: Quality
    ) = BackstagePassItem(newName, newSellIn, newQuality)
}
