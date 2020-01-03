package com.gildedrose

class GildedRose(var items: Array<Item>) {
    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val LEGENDARY = "Sulfuras, Hand of Ragnaros"
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0
    }

    fun updateInventory() = items.forEach(::updateItem)

    private fun updateItem(item: Item) {
        item.sellIn = updatedSellByDate(item)
        item.quality = updatedQuality(item)
    }

    private fun updatedSellByDate(item: Item) =
        if (isLegendary(item))
            item.sellIn
        else
            item.sellIn - 1

    private fun updatedQuality(item: Item): Int {
        if (isLegendary(item))
            return item.quality

        var qualityDifference = MIN_QUALITY
        if (isRegular(item)) {
            qualityDifference = -1

            if (sellByDatePassed(item.sellIn)) {
                qualityDifference = -2
            }
        }

        var newQuality = item.quality + qualityDifference

        if (isAgedBrie(item)) {
            newQuality = agedBrieQuality(item)
        }

        if (isBackstagePass(item)) {
            newQuality = backstagePassQuality(item)
        }

        return validatedQuality(newQuality)
    }

    private fun validatedQuality(newQuality: Int) = when {
        exceedsMaximumQuality(newQuality) -> MAX_QUALITY
        belowMinimumQuality(newQuality) -> MIN_QUALITY
        else -> newQuality
    }

    private fun backstagePassQuality(item: Item) = when {
        sellByDatePassed(item.sellIn) -> 0
        item.sellIn <= 5 -> item.quality + 3
        item.sellIn <= 10 -> item.quality + 2
        else -> item.quality + 1
    }

    private fun agedBrieQuality(item: Item) = item.quality + 1

    private fun sellByDatePassed(daysLeft: Int) = daysLeft < 0
    private fun belowMinimumQuality(quality: Int) = quality < MIN_QUALITY
    private fun exceedsMaximumQuality(quality: Int) = quality > MAX_QUALITY
    private fun isRegular(item: Item) = !isAgedBrie(item) && !isBackstagePass(item) && !isLegendary(item)
    private fun isAgedBrie(item: Item) = item.name == AGED_BRIE
    private fun isLegendary(item: Item) = item.name == LEGENDARY
    private fun isBackstagePass(item: Item) = item.name == BACKSTAGE_PASS
}

