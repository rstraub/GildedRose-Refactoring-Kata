package com.gildedrose

class Quality(points: Int) {
    companion object {
        private const val LEGENDARY_POINTS = 80
        private const val MAX_POINTS = 50
        private const val MIN_POINTS = 0

        val MAX_QUALITY = Quality(MAX_POINTS)
        val MIN_QUALITY = Quality(MIN_POINTS)
    }

    val points: Int

    init {
        this.points = when {
            points == LEGENDARY_POINTS -> LEGENDARY_POINTS
            points > MAX_POINTS -> MAX_POINTS
            points < MIN_POINTS -> MIN_POINTS
            else -> points
        }
    }

    operator fun plus(amount: Int) = Quality(points + amount)
    operator fun minus(amount: Int) = Quality(points - amount)
}
