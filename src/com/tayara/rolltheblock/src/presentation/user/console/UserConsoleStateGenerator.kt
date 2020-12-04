package com.tayara.rolltheblock.src.presentation.user.console

import com.tayara.rolltheblock.src.core.model.*
import com.tayara.rolltheblock.src.presentation.gameplay.StateGenerator

class UserConsoleStateGenerator : StateGenerator() {

    private var rows = 0
    private var columns = 0

    private val tileCharMapper =
        TileConsoleMapper()

    override fun generate(): State {

        readDimensions()

        val grid = readGrid()

        val playerPosition = readPlayerPosition()

        return State(grid, playerPosition)
    }

    private fun readDimensions() {
        println("Enter grid dimensions: ")

        rows = readLine()!!.toInt()
        columns = readLine()!!.toInt()
    }

    private fun readPlayerPosition(): PlayerPosition {
        println("Enter player positions split by space, ex (0,${rows - 1} 1,${rows - 1}): ")

        val positionsString = readLine()!!

        val positions = positionsString.split(" ").map {
            val temp = it.split(",")
            Position2D(temp[0].toInt(), temp[1].toInt())
        }

        return PlayerPosition(*positions.toTypedArray())
    }

    private fun readGrid(): Grid<Tile> {
        println(
            "Enter the grid, '#' for a land, 'O' for a hole and '.' for a space, ex: \n" +
                    "...###..\n" +
                    "###..O..\n" +
                    "..######\n"
        )

        val grid = Grid<Tile>(rows, columns)

        for (i in 0 until rows) {
            val line = readLine()!!

            line.forEachIndexed { index, c ->
                grid[i, index] = tileCharMapper.map(c)
            }

        }

        return grid
    }
}