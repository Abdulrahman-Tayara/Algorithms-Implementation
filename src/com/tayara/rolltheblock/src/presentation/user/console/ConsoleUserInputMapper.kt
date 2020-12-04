package com.tayara.rolltheblock.src.presentation.user.console

import com.tayara.rolltheblock.src.core.model.Mapper
import com.tayara.rolltheblock.src.presentation.input.UserInput

class ConsoleUserInputMapper : Mapper<Char, UserInput>() {

    override fun map(c: Char): UserInput {
        return when(c) {
            'w', 'W' -> UserInput.UP
            'a', 'A' -> UserInput.LEFT
            'd', 'D' -> UserInput.RIGHT
            's', 'S' -> UserInput.DOWN
            else -> throw IllegalArgumentException("Unknown input $c")
        }
    }

    override fun unmap(model: UserInput): Char {
        TODO("Not yet implemented")
    }
}