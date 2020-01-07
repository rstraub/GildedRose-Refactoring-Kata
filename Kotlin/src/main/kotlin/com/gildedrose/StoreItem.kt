package com.gildedrose

import com.gildedrose.Quality.Companion.MAX_POINTS

abstract class StoreItem(
    val name: String,
    val sellIn: Int,
    val quality: Quality
) {
    companion object {
        fun fromItem(item: Item) =
            when {
                item.isAgedBrie() -> AgedItem(item)
                item.isLegendary() -> LegendaryItem(item)
                item.isBackstagePass() -> BackstagePassItem(item)
                item.isConjured() -> ConjuredItem(item)
                else -> RegularItem(item)
            }

        private fun Item.isAgedBrie() = name == "Aged Brie"
        private fun Item.isLegendary() = quality > MAX_POINTS
        private fun Item.isBackstagePass() = name.contains("Backstage passes")
        private fun Item.isConjured() = name.startsWith("Conjured")
    }

    protected val sellByDatePassed = sellIn < 0

    fun updatedItem() =
        copy(newSellIn = calculateSellByDate(), newQuality = updatedQuality())

    private fun updatedQuality() = withNewSellIn().calculateQuality()
    private fun withNewSellIn() = copy(newSellIn = calculateSellByDate())

    protected open fun calculateSellByDate() = sellIn - 1
    protected abstract fun calculateQuality(): Quality

    protected abstract fun copy(
        newName: String = name,
        newSellIn: Int = sellIn,
        newQuality: Quality = quality
    ): StoreItem
}
