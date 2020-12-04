package com.tayara.rolltheblock.src.core.model

@Suppress("UNCHECKED_CAST")
class PlayerPosition(vararg val positions: Position2D) {

    fun standUp() = positions.size == 1

    fun direction(): Direction? {
        return when {
            standUp() -> Direction.STAND_UP
            positions[0].x == positions[1].x -> Direction.HORIZONTAL
            positions[0].y == positions[1].y -> Direction.VERTICAL
            else -> null
        }
    }

    fun up(offset: Int): PlayerPosition =
        PlayerPosition(
            *positions.asList().stream().map { it.up(offset) }.toArray<Position2D> { arrayOfNulls(it) }
        )

    fun down(offset: Int): PlayerPosition =
        PlayerPosition(
            *positions.asList().stream().map { it.down(offset) }.toArray<Position2D> { arrayOfNulls(it) }
        )

    fun left(offset: Int): PlayerPosition =
        PlayerPosition(
            *positions.asList().stream().map { it.left(offset) }.toArray<Position2D> { arrayOfNulls(it) }
        )

    fun right(offset: Int): PlayerPosition =
        PlayerPosition(
            *positions.asList().stream().map { it.right(offset) }.toArray<Position2D> { arrayOfNulls(it) }
        )


    enum class Direction {
        VERTICAL, HORIZONTAL, STAND_UP
    }

    override fun toString(): String {
        return positions.asList().joinToString { it.toString() }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is PlayerPosition) {
            positions.contentEquals(other.positions)
        } else
            super.equals(other)
    }
}

