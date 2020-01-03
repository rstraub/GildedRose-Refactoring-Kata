package com.gildedrose

abstract class StoreItem(
    val name: String,
    val sellIn: Int,
    val quality: Int
) {
    companion object {
        private const val AGED_BRIE = "Aged Brie"
        private const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        private const val LEGENDARY = "Sulfuras, Hand of Ragnaros"
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0

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
    abstract fun calculateQuality(): Int

    protected fun validatedQuality(newQuality: Int) = when {
        exceedsMaximumQuality(newQuality) -> MAX_QUALITY
        belowMinimumQuality(newQuality) -> MIN_QUALITY
        else -> newQuality
    }

    private fun belowMinimumQuality(quality: Int) = quality < MIN_QUALITY
    private fun exceedsMaximumQuality(quality: Int) = quality > MAX_QUALITY

    abstract fun copy(
        newName: String = name,
        newSellIn: Int = sellIn,
        newQuality: Int = quality
    ): StoreItem
}
