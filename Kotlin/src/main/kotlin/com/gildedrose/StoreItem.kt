package com.gildedrose

abstract class StoreItem(
    val name: String,
    val sellIn: Int,
    val quality: Quality
) {
    companion object {
        private const val AGED_BRIE = "Aged Brie"
        private const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        private const val LEGENDARY = "Sulfuras, Hand of Ragnaros"

        fun fromItem(item: Item) =
            when {
                item.isAgedBrie() -> AgedItem(item)
                item.isLegendary() -> LegendaryItem(item)
                item.isBackstagePass() -> BackstagePass(item)
                else -> RegularItem(item)
            }

        private fun Item.isAgedBrie() = name == AGED_BRIE
        private fun Item.isLegendary() = name == LEGENDARY
        private fun Item.isBackstagePass() = name == BACKSTAGE_PASS
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
