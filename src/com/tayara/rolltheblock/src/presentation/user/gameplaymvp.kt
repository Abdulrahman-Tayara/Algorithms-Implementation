package com.tayara.rolltheblock.src.presentation.user

import com.tayara.rolltheblock.src.core.model.Solution
import com.tayara.rolltheblock.src.core.model.SolutionPath
import com.tayara.rolltheblock.src.core.model.State
import com.tayara.rolltheblock.src.presentation.input.UserInput

interface IGameplayPresenter {
    fun acceptInput(userInput: UserInput)
}


interface IGameplayView {
    fun receiveState(state: State)

    fun onGameFinished(solution: Solution, solutionPath: SolutionPath)
}