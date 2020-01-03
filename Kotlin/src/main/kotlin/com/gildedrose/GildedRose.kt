package com.gildedrose

import com.gildedrose.StoreItem.Companion.fromItem

class GildedRose(var items: Array<Item>) {
    private data class UpdatePair(val original: Item, val updated: StoreItem)

    fun updateInventory() = items
        .map(::fromItem)
        .map(StoreItem::withNewSellByDate)
        .map(StoreItem::withNewQuality)
        .mapIndexed(::toUpdatePair)
        .forEach(::updateOriginal)

    private fun toUpdatePair(index: Int, updated: StoreItem) =
        UpdatePair(items[index], updated)

    private fun updateOriginal(updatePair: UpdatePair) =
        updateOriginal(updatePair.original, updatePair.updated)

    private fun updateOriginal(original: Item, updated: StoreItem) =
        original updateWith updated
}

private infix fun Item.updateWith(item: StoreItem) {
    sellIn = item.sellIn
    quality = item.quality
}

