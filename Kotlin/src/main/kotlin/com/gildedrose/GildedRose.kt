package com.gildedrose

import com.gildedrose.StoreItem.Companion.fromItem

class GildedRose(var items: Array<Item>) {
    private data class UpdatePair(val original: Item, val updated: StoreItem)

    fun updateInventory() = items
        .map(::fromItem)
        .map(StoreItem::updatedItem)
        .mapIndexed(::toUpdatePair)
        .forEach(::updateOriginal)

    private fun toUpdatePair(index: Int, updated: StoreItem) =
        UpdatePair(items[index], updated)

    private fun updateOriginal(updatePair: UpdatePair) =
        updatePair.original updateWith updatePair.updated
}

private infix fun Item.updateWith(item: StoreItem) {
    sellIn = item.sellIn
    quality = item.quality.points
}

