package com.tayara.rolltheblock.src.presentation.gameplay

import com.tayara.rolltheblock.src.core.model.*
import com.tayara.rolltheblock.src.presentation.user.console.TileConsoleMapper
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FileStateGenerator(private val path: String) : StateGenerator() {

    private val tileCharMapper = TileConsoleMapper()

    override fun generate(): State {
        var rows: Int
        var columns: Int


        val reader = BufferedReader(FileReader(path))

        val coordinates = reader.readLine()!!.split(' ').map { s -> s.toInt() }
        rows = coordinates[0]
        columns = coordinates[1]
        val grid = Grid<Tile>(rows, columns)

        for (i in 0 until rows) {
            val columnsLine = reader.readLine()

            columnsLine.forEachIndexed { index, c ->
                grid[i, index] = tileCharMapper.map(c)
            }
        }

        val positions = reader.readLine().split(" ").map {
            val temp = it.split(",")
            Position2D(temp[0].toInt(), temp[1].toInt())
        }

        reader.close()
        return State(grid, PlayerPosition(*positions.toTypedArray()))
    }
}