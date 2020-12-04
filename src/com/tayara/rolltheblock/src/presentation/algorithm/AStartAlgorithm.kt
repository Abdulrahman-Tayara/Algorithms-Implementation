package com.tayara.rolltheblock.src.presentation.algorithm

import com.tayara.rolltheblock.src.core.model.Position2D
import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.input.UserInput
import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap

class AStartAlgorithm : Algorithm() {

    // { {cost + evaluation, cost}, state }
    private val queue = PriorityQueue<Pair<Pair<Int, Int>, State>> { o1, o2 ->
        o1.first.first - o2.first.first
    }

    private val distances = HashMap<State, Int>()

    private val actions = arrayOf(UserInput.UP, UserInput.LEFT, UserInput.DOWN, UserInput.RIGHT)

    private var goalPosition: Position2D? = null

    override fun start() {
        goalPosition = initialState.goalPosition()

        queue.add(Pair(Pair(evaluation(initialState), 0), initialState))
        distances[initialState] = 0

        while (!queue.isEmpty()) {
            val top = queue.remove()

            val parentState = top?.second

            parentState?.let {
                if (controller.isFinished(it)) {
                    println("Visited = ${distances.size}")
                    println("Cost = ${top.first.second}")
                    printStates(it)
                    return
                }

                if (distanceTo(parentState) < top.first.second)
                    return@let
                else {
                    distances[parentState] = top.first.second
                }



                val parentCost = top.first

                val states = actions.mapNotNull { action -> controller.applyMove(action, parentState) }

                states.forEach { child ->
                    val costToMove = 1 + parentCost.second
                    val costToMoveWithEvaluation = costToMove + evaluation(child)

                    if (costToMoveWithEvaluation < distanceTo(child)) {
                        queue.add(Pair(Pair(costToMoveWithEvaluation, costToMove), child))
                    }
                }
            }
        }
    }

    private fun distanceTo(state: State): Int {
        return distances[state] ?: Int.MAX_VALUE
    }

    private fun evaluation(state: State): Int = state.playerPosition.positions[0].distance(goalPosition!!)
}