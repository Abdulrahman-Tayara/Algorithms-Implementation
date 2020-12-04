package com.tayara.rolltheblock.src.core.model

class SolutionPath {
    private val states: ArrayList<State> = ArrayList()

    fun addState(state: State) = states.add(state)

    fun removeLast() {
        if (states.size > 1)
            states.removeAt(states.lastIndex)
    }

    fun last() = states.last()
}