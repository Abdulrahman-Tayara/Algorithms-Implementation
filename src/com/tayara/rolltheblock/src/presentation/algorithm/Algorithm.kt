package com.tayara.rolltheblock.src.presentation.algorithm

import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.gameplay.FileStateGenerator
import com.tayara.rolltheblock.src.presentation.input.UserInputActionMapper
import com.tayara.rolltheblock.src.presentation.user.console.TileConsoleMapper
import com.tayara.rolltheblock.src.presentation.user.console.UserConsoleStateGenerator

abstract class Algorithm {

    protected val stateGenerator = FileStateGenerator("state.txt")

    protected val initialState = stateGenerator.generate()

    protected val controller = AlgorithmController(initialState, UserInputActionMapper())

    private val tileConsoleMapper = TileConsoleMapper()

    abstract fun start()

    fun printStates(state: State) {
        var temp: State? = state
        while (temp?.parentState != null) {
            printState(temp)
            temp = temp.parentState
            println()
        }
    }

    fun printState(state: State) {
        val grid = state.grid
        val playerPositions = state.playerPosition.positions

        for (i in 0 until grid.rows) {
            for (j in 0 until grid.columns) {
                if (playerPositions.find { (it.x == i) and (it.y == j) } != null)
                    print('P')
                else
                    print(tileConsoleMapper.unmap(grid[i, j]!!))
            }
            println()
        }
    }

}