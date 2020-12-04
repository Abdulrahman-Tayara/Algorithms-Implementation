package com.tayara.rolltheblock.src.presentation.algorithm

import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.input.UserInput
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.cos

class UCSAlgorithm : Algorithm() {

    private val queue = PriorityQueue<Pair<Int, State>> { o1, o2 -> o1.first - o2.first }

    private val distances = HashMap<State, Int>()

    private val actions = arrayOf(UserInput.UP, UserInput.DOWN, UserInput.RIGHT, UserInput.LEFT)

    override fun start() {
        queue.add(Pair(0, initialState))
        distances[initialState] = 0

        while (!queue.isEmpty()) {
            val top = queue.remove()

            val parentCost = top?.first ?: 0
            val parentState = top?.second

            parentState?.let { it ->
                if (controller.isFinished(it)) {
                    println("Cost = $parentCost")
                    printStates(it)
                    return
                }

                val states = actions.mapNotNull { action -> controller.applyMove(action, parentState) }

                states.forEach { child ->
                    val cost = 1 + parentCost
                    if (cost < cost(child)) {
                        distances[child] = cost
                        queue.add(Pair(cost, child))
                    }
                }
            }
        }
    }

    private fun cost(state: State): Int {
        return distances[state] ?: Int.MAX_VALUE
    }
}