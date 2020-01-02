package com.gildedrose

class GildedRose(var items: Array<Item>) {
    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val LEGENDARY = "Sulfuras, Hand of Ragnaros"
    }

    fun updateInventory() = items.forEach(::updateItem)

    private fun updateItem(item: Item) {
        if (!isAgedBrie(item) && !isBackstagePass(item)) {
            if (item.quality > 0) {
                if (!isLegendary(item)) {
                    item.quality = item.quality - 1
                }
            }
        } else {
            if (canIncreaseInQuality(item)) {
                item.quality = item.quality + 1

                if (isBackstagePass(item)) {
                    if (item.sellIn < 11) {
                        if (canIncreaseInQuality(item)) {
                            item.quality = item.quality + 1
                        }
                    }

                    if (item.sellIn < 6) {
                        if (canIncreaseInQuality(item)) {
                            item.quality = item.quality + 1
                        }
                    }
                }
            }
        }

        if (!isLegendary(item)) {
            item.sellIn = item.sellIn - 1
        }

        if (item.sellIn < 0) {
            if (!isAgedBrie(item)) {
                if (!isBackstagePass(item)) {
                    if (item.quality > 0) {
                        if (!isLegendary(item)) {
                            item.quality = item.quality - 1
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality
                }
            } else {
                if (canIncreaseInQuality(item)) {
                    item.quality = item.quality + 1
                }
            }
        }
    }

    private fun canIncreaseInQuality(item: Item) = item.quality < 50

    private fun isAgedBrie(item: Item) = item.name == AGED_BRIE

    private fun isLegendary(item: Item) = item.name == LEGENDARY

    private fun isBackstagePass(item: Item) = item.name == BACKSTAGE_PASS
}

