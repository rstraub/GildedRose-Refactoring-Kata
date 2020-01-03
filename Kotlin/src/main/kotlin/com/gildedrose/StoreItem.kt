package com.gildedrose

open class StoreItem(
    name: String,
    sellIn: Int,
    quality: Int
) : Item(name, sellIn, quality) {
    companion object {
        fun fromItem(item: Item) : StoreItem {
            return when(item) {
                ::isAgedBrie -> AgedItem(item.name, item.sellIn, item.quality)
                else -> StoreItem(item.name, item.sellIn, item.quality)
            }
        }


        private fun isAgedBrie(item: Item) = item.name == GildedRose.AGED_BRIE
        private fun isLegendary(item: Item) = item.name == GildedRose.LEGENDARY
        private fun isBackstagePass(item: Item) = item.name == GildedRose.BACKSTAGE_PASS
    }
}
