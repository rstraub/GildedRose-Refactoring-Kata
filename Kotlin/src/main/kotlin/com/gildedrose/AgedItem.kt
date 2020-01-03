package com.gildedrose

class AgedItem(
    name: String,
    sellIn: Int,
    quality: Int
) : StoreItem(name, sellIn, quality) {
    constructor(item: Item) : this(item.name, item.sellIn, item.quality)

    override fun calculateQuality() = validatedQuality(quality + 1)

    override fun copy(
        newName: String,
        newSellIn: Int,
        newQuality: Int
    ) = AgedItem(newName, newSellIn, newQuality)
}
