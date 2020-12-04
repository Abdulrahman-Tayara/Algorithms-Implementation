package com.tayara.rolltheblock.src.presentation.user.console

import com.tayara.rolltheblock.src.core.model.*

class TileConsoleMapper : Mapper<Char, Tile>() {

    override fun map(c: Char): Tile {
        return when (c) {
            '.' -> Space
            'O', 'o' -> Hole
            else -> Land
        }
    }

    override fun unmap(tile: Tile): Char {
        return when (tile) {
            Space -> '.'
            Hole -> 'O'
            else -> '#'
        }
    }
}