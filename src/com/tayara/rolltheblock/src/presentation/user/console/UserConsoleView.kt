package com.tayara.rolltheblock.src.presentation.user.console

import com.tayara.rolltheblock.src.core.model.Solution
import com.tayara.rolltheblock.src.core.model.SolutionPath
import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.user.GameplayPresenterImpl
import com.tayara.rolltheblock.src.presentation.user.IGameplayPresenter
import com.tayara.rolltheblock.src.presentation.user.IGameplayView
import com.tayara.rolltheblock.src.presentation.input.UserInputActionMapper

class UserConsoleView : IGameplayView {

    private val state = UserConsoleStateGenerator().generate()

    private val consoleInputMapper = ConsoleUserInputMapper()

    private val tileConsoleMapper = TileConsoleMapper()

    private var finished = false

    private val presenter: IGameplayPresenter =
        GameplayPresenterImpl(
            state,
            this,
            UserInputActionMapper()
        )

    init {
        handleInput()
    }

    private fun handleInput() {
        while (!finished) {
            var input: Char
            try {
                input = readLine()!![0]
            } catch (e: Exception) {
                println("Wrong input.")
                continue
            }
            presenter.acceptInput(consoleInputMapper.map(input))
        }
        println("!!!! Congratulations !!!!")
    }

    override fun receiveState(state: State) {
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

    override fun onGameFinished(solution: Solution, solutionPath: SolutionPath) {
        finished = true
    }


}