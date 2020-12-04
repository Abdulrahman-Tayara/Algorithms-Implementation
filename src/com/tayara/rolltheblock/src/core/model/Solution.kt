package com.tayara.rolltheblock.src.core.model

class Solution {
    private val actions: ArrayList<Action> = ArrayList()

    fun addAction(action: Action) = actions.add(action)

    fun removeLast() {
        if (actions.size > 1)
            actions.removeAt(actions.lastIndex)
    }

    fun last() = actions.last()
}