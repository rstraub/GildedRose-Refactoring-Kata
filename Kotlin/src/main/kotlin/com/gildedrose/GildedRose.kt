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
        item.quality = updatedQuality(item)
        item.sellIn = updatedSellByDate(item)
    }

    private fun updatedQuality(item: Item): Int {
        if (isLegendary(item))
            return item.quality

        var qualityDifference = MIN_QUALITY
        if (isRegular(item)) {
            qualityDifference = -1
        }

        if (isAged(item)) {
            qualityDifference = 1

            if (isBackstagePass(item)) {
                if (item.sellIn <= 10) {
                    qualityDifference = 2
                }

                if (item.sellIn <= 5) {
                    qualityDifference = 3
                }
            }
        }

        if (sellByDatePassed(updatedSellByDate(item))) {
            if (isBackstagePass(item)) {
                qualityDifference = -item.quality
            } else {
                if (isRegular(item)) {
                    qualityDifference = -2
                }
            }
        }

        val newQuality = item.quality + qualityDifference

        return if (newQuality > MAX_QUALITY)
            MAX_QUALITY
        else if (newQuality < MIN_QUALITY)
            MIN_QUALITY
        else {
            newQuality
        }
    }

    private fun updatedSellByDate(item: Item) =
        if (isLegendary(item))
            item.sellIn
        else
            item.sellIn - 1

    private fun sellByDatePassed(daysLeft: Int) = daysLeft < 0

    private fun isAllowedToIncreaseQuality(item: Item) =
        !isLegendary(item) && isBeneathMaximumQuality(item.quality)

    private fun isBeneathMaximumQuality(quality: Int) = quality < MAX_QUALITY

    private fun isRegular(item: Item) = !isAged(item) && !isLegendary(item)

    private fun isAged(item: Item) = isAgedBrie(item) || isBackstagePass(item)

    private fun isAgedBrie(item: Item) = item.name == AGED_BRIE

    private fun isLegendary(item: Item) = item.name == LEGENDARY

    private fun isBackstagePass(item: Item) = item.name == BACKSTAGE_PASS
}

