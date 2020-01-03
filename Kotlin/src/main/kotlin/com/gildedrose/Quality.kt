package com.gildedrose

class Quality(points: Int) {
    private companion object {
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0

        private const val MIN_POINTS = 0
        private const val MAX_POINTS = 0
        private const val LEGENDARY_POINTS = 80
    }

    val points: Int

    init {
        this.points = when {
            points == LEGENDARY_POINTS -> LEGENDARY_POINTS
            points > MAX_QUALITY -> MAX_QUALITY
            points < MIN_QUALITY -> MIN_QUALITY
            else -> points
        }
    }

    operator fun plus(amount: Int) = Quality(points + amount)
    operator fun minus(amount: Int) = Quality(points - amount)
}
