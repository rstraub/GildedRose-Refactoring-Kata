package com.gildedrose

class GildedRose(var items: Array<Item>) {
    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val LEGENDARY = "Sulfuras, Hand of Ragnaros"
    }

    fun updateInventory() = items.forEach(::updateItem)

    private fun updateItem(item: Item) {
        if (item.name != AGED_BRIE && item.name != BACKSTAGE_PASS) {
            if (item.quality > 0) {
                if (item.name != LEGENDARY) {
                    item.quality = item.quality - 1
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1

                if (item.name == BACKSTAGE_PASS) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }
                }
            }
        }

        if (item.name != LEGENDARY) {
            item.sellIn = item.sellIn - 1
        }

        if (item.sellIn < 0) {
            if (item.name != AGED_BRIE) {
                if (item.name != BACKSTAGE_PASS) {
                    if (item.quality > 0) {
                        if (item.name != LEGENDARY) {
                            item.quality = item.quality - 1
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1
                }
            }
        }
    }
}

