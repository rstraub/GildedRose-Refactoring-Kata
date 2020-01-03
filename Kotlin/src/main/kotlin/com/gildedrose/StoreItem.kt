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
                item.isBackstagePass() -> BackstagePass(item)
                else -> RegularItem(item)
            }

        private fun Item.isAgedBrie() = name == "Aged Brie"
        private fun Item.isLegendary() = quality > MAX_POINTS
        private fun Item.isBackstagePass() = name.contains("Backstage passes")
    }

    val sellByDatePassed = sellIn < 0

    fun withNewSellByDate() = copy(newSellIn = calculateSellByDate())
    open fun calculateSellByDate() = sellIn - 1

    fun withNewQuality() = copy(newQuality = calculateQuality())
    abstract fun calculateQuality(): Quality

    abstract fun copy(
        newName: String = name,
        newSellIn: Int = sellIn,
        newQuality: Quality = quality
    ): StoreItem
}
