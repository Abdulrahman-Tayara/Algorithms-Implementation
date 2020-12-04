package com.tayara.rolltheblock.src.presentation.input

import com.tayara.rolltheblock.src.core.action.DownAction
import com.tayara.rolltheblock.src.core.action.LeftAction
import com.tayara.rolltheblock.src.core.action.RightAction
import com.tayara.rolltheblock.src.core.action.UpAction
import com.tayara.rolltheblock.src.core.model.Action
import com.tayara.rolltheblock.src.core.model.Mapper

class UserInputActionMapper : Mapper<UserInput, Action>() {

    override fun map(model: UserInput): Action {
        return when(model) {
            UserInput.UP -> UpAction()
            UserInput.DOWN -> DownAction()
            UserInput.LEFT -> LeftAction()
            UserInput.RIGHT -> RightAction()
            else -> throw IllegalStateException("Unknown user input $model")
        }
    }

    override fun unmap(model: Action): UserInput {
        TODO("Not yet implemented")
    }
}