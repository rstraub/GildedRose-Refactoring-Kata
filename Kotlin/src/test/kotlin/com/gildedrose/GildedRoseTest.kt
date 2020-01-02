package com.gildedrose

import com.gildedrose.GildedRose.Companion.AGED_BRIE
import com.gildedrose.GildedRose.Companion.BACKSTAGE_PASS
import com.gildedrose.GildedRose.Companion.LEGENDARY
import com.gildedrose.GildedRose.Companion.MAX_QUALITY
import com.gildedrose.GildedRose.Companion.MIN_QUALITY
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    private val regularItem = Item("test", 10, 10)

    @Test
    fun `should reduce the sell in days`() {
        val result = updatedItem(regularItem)

        result assertSellInIs 9
    }

    @Test
    fun `quality of an item should never be below the minimum quality`() {
        val item = Item("test", 10, 0)

        val result = updatedItem(item)

        assertThat(MIN_QUALITY).isEqualTo(0)
        result assertQualityIs MIN_QUALITY
    }

    @Test
    internal fun `quality should not exceed maximum quality`() {
        val item = Item(AGED_BRIE, 10, MAX_QUALITY)

        val result = updatedItem(item)

        assertThat(MAX_QUALITY).isEqualTo(50)
        result assertQualityIs MAX_QUALITY
    }

    @Nested
    inner class Regular {
        @Test
        fun `should reduce in quality`() {
            val result = updatedItem(regularItem)

            result assertQualityIs 9
        }

        @Test
        fun `quality should degrade twice as fast when sell by date has passed`() {
            val item = Item("test", 0, 10)

            val result = updatedItem(item)

            result assertQualityIs 8
        }
    }

    @Nested
    inner class AgedBrie {
        @Test
        internal fun `quality should increase over time`() {
            val item = Item(AGED_BRIE, 10, 49)

            val result = updatedItem(item)

            result assertQualityIs MAX_QUALITY
        }
    }

    @Nested
    inner class Legendary {
        private lateinit var legendary: Item

        @BeforeEach
        internal fun setUp() {
            legendary = Item(LEGENDARY, 0, 80)
        }

        @Test
        internal fun `quality should not degrade`() {
            val result = updatedItem(legendary)

            result assertQualityIs 80
        }

        @Test
        internal fun `quality should not increase`() {
            val item = Item(LEGENDARY, 10, 40)

            val result = updatedItem(item)

            result assertQualityIs 40
        }

        @Test
        internal fun `does not have a sell by date`() {
            val result = updatedItem(legendary)

            result assertSellInIs 0
        }
    }

    @Nested
    inner class BackstagePasses {
        @Test
        internal fun `should have zero quality when the sell by date has passed`() {
            val item = Item(BACKSTAGE_PASS, 0, 49)

            val result = updatedItem(item)

            result assertQualityIs MIN_QUALITY
        }

        @Test
        internal fun `should increase the quality by one if it has more than ten days remaining`() {
            val item = Item(BACKSTAGE_PASS, 11, 49)

            val result = updatedItem(item)

            result assertQualityIs MAX_QUALITY
        }

        @Test
        internal fun `should increase the quality by two when there are less than ten days remaining`() {
            val item = Item(BACKSTAGE_PASS, 6, 48)

            val result = updatedItem(item)

            result assertQualityIs MAX_QUALITY
        }

        @Test
        internal fun `should increase the quality by three when there are less than five days left`() {
            val item = Item(BACKSTAGE_PASS, 5, 47)

            val result = updatedItem(item)

            result assertQualityIs MAX_QUALITY
        }

        @Test
        internal fun `should not be able to exceed maximum quality`() {
            val item = Item(BACKSTAGE_PASS, 5, 49)

            val result = updatedItem(item)

            result assertQualityIs MAX_QUALITY
        }
    }

    private infix fun Item.assertQualityIs(expected: Int) =
        assertThat(quality).isEqualTo(expected)

    private infix fun Item.assertSellInIs(expected: Int) =
        assertThat(sellIn).isEqualTo(expected)

    private fun updatedItem(item: Item): Item {
        val items = arrayOf(item)
        val app = GildedRose(items)
        app.updateInventory()
        return app.items.first()
    }
}
