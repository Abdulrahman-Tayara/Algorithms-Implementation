package com.tayara.rolltheblock.src.core.model

import kotlin.math.roundToInt
import kotlin.math.sqrt

data class Position2D(val x: Int, val y: Int) {

    fun up(offset: Int) = Position2D(x - 1, y)
    fun down(offset: Int) = Position2D(x + 1, y)
    fun right(offset: Int) = Position2D(x, y + 1)
    fun left(offset: Int) = Position2D(x, y - 1)

    operator fun plus(position2D: Position2D) = Position2D(x + position2D.x, y + position2D.y)
    operator fun minus(position2D: Position2D) = Position2D(x - position2D.x, y - position2D.y)


    override fun equals(other: Any?): Boolean {
        return if (other is Position2D) {
            x == other.x && y == other.y
        } else
            super.equals(other)
    }


    fun distance(position2D: Position2D): Int {
        val distanceSquare = ((position2D.x - x) * (position2D.x - x)).toFloat() +
                ((position2D.y - y) * (position2D.y - y)).toFloat()
        return sqrt(distanceSquare).roundToInt()
    }
}


