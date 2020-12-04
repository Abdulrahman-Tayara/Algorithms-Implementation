package com.tayara.rolltheblock.src.core.game

import com.tayara.rolltheblock.src.core.model.*

class Game(
    initialState: State,
    private val onGameFinished: (solution: Solution, solutionPath: SolutionPath) -> Unit
) {

    private val solution = Solution()
    private val solutionPath = SolutionPath()

    private var currentState: State = initialState

    init {
        solutionPath.addState(currentState)
    }

    var finished = false
        private set


    fun acceptAction(action: Action): State? {
        if (finished)
            return currentState

        val newState = action.apply(currentState)

        if (isValidState(newState)) {
            currentState = newState
            solution.addAction(action)
            solutionPath.addState(newState)

            if (isFinishedState(newState)) {
                finished = true
                onGameFinished.invoke(solution, solutionPath)
            }

            return newState
        }

        return null
    }

    fun applyAction(action: Action, state: State): State? {
        val newState = action.apply(state)
        return if (isValidState(newState)) newState else null
    }

    fun undo(): State {
        solutionPath.removeLast()
        solution.removeLast()
        currentState = solutionPath.last()
        finished = isFinishedState(currentState)
        return currentState
    }


    fun isFinishedState(state: State): Boolean {
        val position = state.playerPosition.positions[0]
        val grid = state.grid

        return state.playerPosition.standUp() && grid[position.x, position.y] is Hole
    }


    private fun isValidState(state: State): Boolean {
        val playerPosition = state.playerPosition
        val grid = state.grid
        return state.isPlayerInGrid && !playerPosition.positions.any { grid[it.x, it.y] is Space }
    }

}