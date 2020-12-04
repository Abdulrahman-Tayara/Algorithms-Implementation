package com.tayara.rolltheblock.src.presentation.algorithm

import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.input.UserInput
import com.tayara.rolltheblock.src.presentation.input.UserInputActionMapper
import com.tayara.rolltheblock.src.presentation.user.console.TileConsoleMapper
import com.tayara.rolltheblock.src.presentation.user.console.UserConsoleStateGenerator
import kotlin.system.exitProcess

class DFSAlgorithm : Algorithm() {

    private val closedStates = ArrayList<State>()

    private val actions = arrayOf(UserInput.UP, UserInput.DOWN, UserInput.RIGHT, UserInput.LEFT)

    override fun start() {
        startGame(initialState)
    }


    private fun startGame(state: State) {
        if (controller.isFinished(state)) {
            printStates(state)
            exitProcess(0)
        }

        if (closedStates.contains(state))
            return

        closedStates.add(state)

        val newStates = actions.mapNotNull { action -> controller.applyMove(action, state) }

        for (newState in newStates) {
            startGame(newState)
        }

    }
}