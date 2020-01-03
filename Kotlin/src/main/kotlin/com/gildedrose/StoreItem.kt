package com.gildedrose

open class StoreItem(
    name: String,
    sellIn: Int,
    quality: Int
) : Item(name, sellIn, quality) {
    companion object {
        private const val AGED_BRIE = "Aged Brie"
        private const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        private const val LEGENDARY = "Sulfuras, Hand of Ragnaros"
        private const val MAX_QUALITY = 50
        private const val MIN_QUALITY = 0

        fun fromItem(item: Item) =
            when {
                isAgedBrie(item) -> AgedItem(item.name, item.sellIn, item.quality)
                isLegendary(item) -> LegendaryItem(item.name, item.sellIn, item.quality)
                isBackstagePass(item) -> BackstagePass(item.name, item.sellIn, item.quality)
                else -> StoreItem(item.name, item.sellIn, item.quality)
            }

        private fun isAgedBrie(item: Item) = item.name == AGED_BRIE
        private fun isLegendary(item: Item) = item.name == LEGENDARY
        private fun isBackstagePass(item: Item) = item.name == BACKSTAGE_PASS
    }

    open fun withNewSellByDate() = copy(newSellIn = sellIn - 1)

    open fun withNewQuality(): StoreItem {
        val newQuality = when {
            isAgedBrie(this) -> validatedQuality(agedBrieQuality(this))
            isBackstagePass(this) -> validatedQuality(backstagePassQuality(this))
            else -> validatedQuality(regularQuality(this))
        }

        return StoreItem(name, sellIn, newQuality)
    }

    private fun regularQuality(item: StoreItem) =
        if (sellByDatePassed(item.sellIn))
            item.quality - 2
        else
            item.quality - 1

    private fun backstagePassQuality(item: StoreItem) = when {
        sellByDatePassed(item.sellIn) -> 0
        item.sellIn <= 5 -> item.quality + 3
        item.sellIn <= 10 -> item.quality + 2
        else -> item.quality + 1
    }

    private fun agedBrieQuality(item: StoreItem) = item.quality + 1

    private fun validatedQuality(newQuality: Int) = when {
        exceedsMaximumQuality(newQuality) -> MAX_QUALITY
        belowMinimumQuality(newQuality) -> MIN_QUALITY
        else -> newQuality
    }

    private fun sellByDatePassed(daysLeft: Int) = daysLeft < 0
    private fun belowMinimumQuality(quality: Int) = quality < MIN_QUALITY
    private fun exceedsMaximumQuality(quality: Int) = quality > MAX_QUALITY

    protected open fun copy(
        newName: String = name,
        newSellIn: Int = sellIn,
        newQuality: Int = quality
    ): StoreItem = StoreItem(newName, newSellIn, newQuality)
}
