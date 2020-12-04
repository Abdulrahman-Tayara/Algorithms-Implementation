package com.tayara.rolltheblock.src.presentation.user

import com.tayara.rolltheblock.src.core.game.Game
import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.input.UserInput
import com.tayara.rolltheblock.src.presentation.input.UserInputActionMapper

class GameplayPresenterImpl(
    initialState: State,
    private val view: IGameplayView,
    private val inputActionMapper: UserInputActionMapper
) : IGameplayPresenter {

    private val game = Game(initialState) { solution, solutionPath ->
        view.onGameFinished(solution, solutionPath)
    }

    init {
        view.receiveState(initialState)
    }

    override fun acceptInput(userInput: UserInput) {
        val action = inputActionMapper.map(userInput)

        val newState = game.acceptAction(action)

        if (!game.finished)
            newState?.let { view.receiveState(it) }
    }

}