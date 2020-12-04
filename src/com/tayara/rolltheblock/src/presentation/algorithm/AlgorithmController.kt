package com.tayara.rolltheblock.src.presentation.algorithm

import com.tayara.rolltheblock.src.core.game.Game
import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.input.UserInput
import com.tayara.rolltheblock.src.presentation.input.UserInputActionMapper

class AlgorithmController(
    private val initialState: State,
    private val inputActionMapper: UserInputActionMapper
) {


    private val game = Game(initialState) { _, _ ->

    }

    fun applyMove(userInput: UserInput, state: State): State? {
        return game.applyAction(inputActionMapper.map(userInput), state)
    }

    fun isFinished(state: State) = game.isFinishedState(state)

}
