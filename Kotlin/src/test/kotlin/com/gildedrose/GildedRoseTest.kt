package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    private val regularItem = Item("test", 10, 10)

    @Test
    fun `should deduct the sell in days`() {
        val items = arrayOf(regularItem)

        val result = updatedItem(items)

        result assertSellInIs 9
    }

    @Test
    fun `should deduct the quality for a regular item`() {
        val items = arrayOf(regularItem)

        val result = updatedItem(items)

        result assertQualityIs 9
    }

    @Test
    fun `quality of an item should never become negative`() {
        val items = arrayOf(Item("test", 10, 0))

        val result = updatedItem(items)

        result assertQualityIs 0
    }

    @Test
    fun `quality should degrade twice as fast when sell by date has passed`() {
        val items = arrayOf(Item("test", 0, 10))

        val result = updatedItem(items)

        result assertQualityIs 8
    }

    @Nested
    inner class AgedBrie {
        private val agedBrie = "Aged Brie"

        @Test
        internal fun `quality should increase over time`() {
            val item = Item(agedBrie, 10, 49)
            val items = arrayOf(item)
            val result = updatedItem(items)

            result assertQualityIs 50
        }

        @Test
        internal fun `quality should not exceed 50`() {
            val item = Item(agedBrie, 10, 50)
            val items = arrayOf(item)

            val result = updatedItem(items)

            result assertQualityIs 50
        }
    }

    @Nested
    inner class Legendary {
        private lateinit var legendary: Item

        @BeforeEach
        internal fun setUp() {
            legendary = Item("Sulfuras, Hand of Ragnaros", 0, 80)
        }

        @Test
        internal fun `quality should not degrade`() {
            val result = updatedItem(arrayOf(legendary))

            result assertQualityIs 80
        }

        @Test
        internal fun `does not have a sell by date`() {
            val result = updatedItem(arrayOf(legendary))

            result assertSellInIs 0
        }
    }

    @Nested
    inner class BackstagePasses {
        private val backStagePass = "Backstage passes to a TAFKAL80ETC concert"

        @Test
        internal fun `should have zero quality when the sell by date has passed`() {
            val backstagePass = Item(backStagePass, 0, 49)

            val result = updatedItem(arrayOf(backstagePass))

            result assertQualityIs 0
        }

        @Test
        internal fun `should increase the quality by one if it has more than 10 days remaining`() {
            val backstagePass = Item(backStagePass, 11, 0)

            val result = updatedItem(arrayOf(backstagePass))

            result assertQualityIs 1
        }

        @Test
        internal fun `should increase the quality by two when there are less than 10 days remaining`() {
            val backstagePass = Item(backStagePass, 10, 0)

            val result = updatedItem(arrayOf(backstagePass))

            result assertQualityIs 2
        }

        @Test
        internal fun `should increase the quality by three when there are less than five days left`() {
            val backstagePass = Item(backStagePass, 5, 0)

            val result = updatedItem(arrayOf(backstagePass))

            result assertQualityIs 3
        }
    }

    private infix fun Item.assertQualityIs(expected: Int) =
        assertThat(quality).isEqualTo(expected)

    private infix fun Item.assertSellInIs(expected: Int) =
        assertThat(sellIn).isEqualTo(expected)

    private fun updatedItem(items: Array<Item>): Item {
        val app = GildedRose(items)

        app.updateQuality()

        return app.items.first()
    }
}
