package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

internal class GildedRoseTest {
    @Test
    fun `should deduct the sell in days`() {
        val items = arrayOf(Item("test", 10, 10))

        val result = updatedItem(items)

        assertEquals(9, result.sellIn)
    }

    @Test
    fun `should deduct the quality for a regular item`() {
        val items = arrayOf(Item("test", 10, 10))

        val result = updatedItem(items)

        assertEquals(9, result.quality)
    }

    @Test
    fun `quality of an item should never become negative`() {
        val items = arrayOf(Item("test", 10, 0))

        val result = updatedItem(items)

        assertEquals(0, result.quality)
    }

    @Test
    fun `quality should degrade twice as fast when sell by date has passed`() {
        val items = arrayOf(Item("test", 0, 10))

        val result = updatedItem(items)

        assertEquals(8, result.quality)
    }

    private fun updatedItem(items: Array<Item>): Item {
        val app = GildedRose(items)

        app.updateQuality()

        return app.items.first()
    }
}


