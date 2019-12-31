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

        assertThat(result.sellIn).isEqualTo(9)
    }

    @Test
    fun `should deduct the quality for a regular item`() {
        val items = arrayOf(regularItem)

        val result = updatedItem(items)

        assertThat(result.quality).isEqualTo(9)
    }

    @Test
    fun `quality of an item should never become negative`() {
        val items = arrayOf(Item("test", 10, 0))

        val result = updatedItem(items)

        assertThat(result.quality).isEqualTo(0)
    }

    @Test
    fun `quality should degrade twice as fast when sell by date has passed`() {
        val items = arrayOf(Item("test", 0, 10))

        val result = updatedItem(items)

        assertThat(result.quality).isEqualTo(8)
    }

    @Nested
    inner class Aged {
        private lateinit var agedBrie: Item
        private lateinit var items: Array<Item>

        @BeforeEach
        internal fun setUp() {
            agedBrie = Item("Aged Brie", 10, 49)
            items = arrayOf(agedBrie)
        }

        @Test
        internal fun `quality should increase over time`() {
            val result = updatedItem(items)

            assertThat(result.quality).isEqualTo(50)
        }

        @Test
        internal fun `quality should not exceed 50`() {
            agedBrie = Item("Aged Brie", 10, 50)
            items = arrayOf(agedBrie)

            val result = updatedItem(items)

            assertThat(result.quality).isEqualTo(50)
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

            assertThat(result.quality).isEqualTo(80)
        }

        @Test
        internal fun `does not have a sell by date`() {
            val result = updatedItem(arrayOf(legendary))

            assertThat(result.sellIn).isEqualTo(0)
        }
    }

    private fun updatedItem(items: Array<Item>): Item {
        val app = GildedRose(items)

        app.updateQuality()

        return app.items.first()
    }
}
