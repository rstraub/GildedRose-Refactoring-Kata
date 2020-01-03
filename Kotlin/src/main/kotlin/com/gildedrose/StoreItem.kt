package com.gildedrose

open class StoreItem(
    name: String,
    sellIn: Int,
    quality: Int
) : Item(name, sellIn, quality) {
    companion object {
        fun fromItem(item: Item) : StoreItem {
            return StoreItem(item.name, item.sellIn, item.quality)
        }
    }
}
