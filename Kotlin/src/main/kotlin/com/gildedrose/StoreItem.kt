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
                isAgedBrie(item) -> AgedItem(item.name, item.sellIn, item.quality)
                isLegendary(item) -> LegendaryItem(item.name, item.sellIn, item.quality)
                isBackstagePass(item) -> BackstagePass(item.name, item.sellIn, item.quality)
                else -> RegularItem(item.name, item.sellIn, item.quality)
            }

        private fun isAgedBrie(item: Item) = item.name == AGED_BRIE
        private fun isLegendary(item: Item) = item.name == LEGENDARY
        private fun isBackstagePass(item: Item) = item.name == BACKSTAGE_PASS
    }

    open fun withNewSellByDate() = copy(newSellIn = sellIn - 1)
    fun withNewQuality() = copy(newQuality = calculateQuality())

    abstract fun calculateQuality(): Int

    protected fun validatedQuality(newQuality: Int) = when {
        exceedsMaximumQuality(newQuality) -> MAX_QUALITY
        belowMinimumQuality(newQuality) -> MIN_QUALITY
        else -> newQuality
    }

    fun sellByDatePassed() = sellIn < 0
    private fun belowMinimumQuality(quality: Int) = quality < MIN_QUALITY
    private fun exceedsMaximumQuality(quality: Int) = quality > MAX_QUALITY

    abstract fun copy(
        newName: String = name,
        newSellIn: Int = sellIn,
        newQuality: Int = quality
    ): StoreItem
}
