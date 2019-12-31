package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

internal class GildedRoseTest {
    @Test
    fun `should deduct the sell in days`() {
        val items = arrayOf(Item("test", 10, 10))
        val app = GildedRose(items)

        app.updateQuality()
        val result = app.items.first()

        assertEquals(9, result.sellIn)
    }

    @Test
    fun `should deduct the quality for a regular item`() {
        val items = arrayOf(Item("test", 10, 10))
        val app = GildedRose(items)

        app.updateQuality()
        val result = app.items.first()

        assertEquals(9, result.quality)
    }

    @Test
    fun `quality of an item can never become negative`() {
        val items = arrayOf(Item("test", 10, 0))
        val app = GildedRose(items)

        app.updateQuality()
        val result = app.items.first()

        assertEquals(0, result.quality)
    }
}


