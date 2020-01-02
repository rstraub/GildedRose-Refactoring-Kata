package com.gildedrose

class GildedRose(var items: Array<Item>) {
    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val LEGENDARY = "Sulfuras, Hand of Ragnaros"
    }

    fun updateInventory() = items.forEach(::updateItem)

    private fun updateItem(item: Item) {
        item.quality = updateQuality(item)
        item.sellIn = updatedSellByDate(item)
    }

    private fun updateQuality(item: Item): Int {
        var qualityDifference = 0
        if (isAged(item)) {
            if (isAllowedToIncreaseQuality(item)) {
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
        } else {
            if (isAllowedToDecreaseQuality(item)) {
                qualityDifference = -1
            }
        }

        if (sellByDatePassed(updatedSellByDate(item))) {
            if (isBackstagePass(item)) {
                qualityDifference = -item.quality
            } else {
                if (isAllowedToDecreaseQuality(item)) {
                    qualityDifference = -2
                }
            }
        }

        val newQuality = item.quality + qualityDifference

        return if (newQuality > 50 && !isLegendary(item))
            50
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

    private fun isAllowedToDecreaseQuality(item: Item) =
        !isLegendary(item) && isAboveMinimumQuality(item.quality)

    private fun isAllowedToIncreaseQuality(item: Item) =
        !isLegendary(item) && isBeneathMaximumQuality(item.quality)

    private fun isBeneathMaximumQuality(quality: Int) = quality < 50

    private fun isAboveMinimumQuality(quality: Int) = quality > 0

    private fun isAged(item: Item) = isAgedBrie(item) || isBackstagePass(item)

    private fun isAgedBrie(item: Item) = item.name == AGED_BRIE

    private fun isLegendary(item: Item) = item.name == LEGENDARY

    private fun isBackstagePass(item: Item) = item.name == BACKSTAGE_PASS
}

