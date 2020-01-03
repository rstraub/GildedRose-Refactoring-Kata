package com.gildedrose

class BackstagePass(
    name: String,
    sellIn: Int,
    quality: Int
) : StoreItem(name, sellIn, quality) {
    override fun calculateQuality(): Int {
        val newQuality = when {
            sellByDatePassed() -> MIN_QUALITY
            sellIn <= 5 -> quality + 3
            sellIn <= 10 -> quality + 2
            else -> quality + 1
        }
        return validatedQuality(newQuality)
    }

    override fun copy(
        newName: String,
        newSellIn: Int,
        newQuality: Int
    ) = BackstagePass(newName, newSellIn, newQuality)
}
