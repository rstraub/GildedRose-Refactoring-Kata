package com.gildedrose

class AgedItem(
    name: String,
    sellIn: Int,
    quality: Quality
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, Quality(item.quality))

    override fun calculateQuality() = quality + 1

    override fun copy(
        newName: String,
        newSellIn: Int,
        newQuality: Quality
    ) = AgedItem(newName, newSellIn, newQuality)
}
