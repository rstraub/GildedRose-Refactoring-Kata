package com.gildedrose

class GildedRose(var items: Array<Item>) {
    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val LEGENDARY = "Sulfuras, Hand of Ragnaros"
    }

    fun updateInventory() = items.forEach(::updateItem)

    private fun updateItem(item: Item) {
        if (isAged(item)) {
            if (isAllowedToIncreaseQuality(item)) {
                item.quality = item.quality + 1

                if (isBackstagePass(item)) {
                    if (item.sellIn < 11) {
                        if (isAllowedToIncreaseQuality(item)) {
                            item.quality = item.quality + 1
                        }
                    }

                    if (item.sellIn < 6) {
                        if (isAllowedToIncreaseQuality(item)) {
                            item.quality = item.quality + 1
                        }
                    }
                }
            }
        } else {
            if (isAllowedToDecreaseQuality(item)) {
                item.quality = item.quality - 1
            }
        }

        updateSellByDate(item)

        if (sellByDatePassed(item)) {
            if (!isAgedBrie(item)) {
                if (!isBackstagePass(item)) {
                    if (isAllowedToDecreaseQuality(item)) {
                        if (!isLegendary(item)) {
                            item.quality = item.quality - 1
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality
                }
            } else {
                if (isAllowedToIncreaseQuality(item)) {
                    item.quality = item.quality + 1
                }
            }
        }
    }

    private fun updateSellByDate(item: Item) {
        if (!isLegendary(item)) {
            item.sellIn = item.sellIn - 1
        }
    }

    private fun sellByDatePassed(item: Item) = item.sellIn < 0

    private fun isAllowedToDecreaseQuality(item: Item) = !isLegendary(item) && item.quality > 0

    private fun isAllowedToIncreaseQuality(item: Item) = item.quality < 50

    private fun isAged(item: Item) = isAgedBrie(item) || isBackstagePass(item)

    private fun isAgedBrie(item: Item) = item.name == AGED_BRIE

    private fun isLegendary(item: Item) = item.name == LEGENDARY

    private fun isBackstagePass(item: Item) = item.name == BACKSTAGE_PASS
}

