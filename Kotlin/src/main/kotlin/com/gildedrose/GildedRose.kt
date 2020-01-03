package com.gildedrose

import com.gildedrose.StoreItem.Companion.fromItem

class GildedRose(var items: Array<Item>) {
    fun updateInventory() = items
        .map(::fromItem)
        .map(StoreItem::withNewSellByDate)
        .map(StoreItem::withNewQuality)
        .forEachIndexed(::updateOriginalItem)

    private fun updateOriginalItem(index: Int, item: Item) {
        val original = items[index]
        original.sellIn = item.sellIn
        original.quality = item.quality
    }
}

