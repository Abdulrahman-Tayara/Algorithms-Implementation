package com.tayara.rolltheblock.src.core.model

import java.util.*


data class State(
    val grid: Grid<Tile>,
    val playerPosition: PlayerPosition,
    var parentState: State? = null
) {

    val isPlayerInGrid: Boolean

    init {
        isPlayerInGrid = checkPlayerPosition()
    }

    fun goalPosition(): Position2D? {
        for (i in 0 until grid.rows)
            for (j in 0 until  grid.columns)
                if (grid[i, j] is Hole)
                    return Position2D(i, j)
        return null
    }

    private fun checkPlayerPosition(): Boolean {
        return !playerPosition.positions.any {
            it.x < 0 || it.x >= grid.rows || it.y < 0 || it.y >= grid.columns
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is State) {
            return playerPosition == other.playerPosition
        }
        return false;
    }
}