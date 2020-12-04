package com.tayara.rolltheblock.src.core.action

import com.tayara.rolltheblock.src.core.model.Action
import com.tayara.rolltheblock.src.core.model.PlayerPosition
import com.tayara.rolltheblock.src.core.model.State

abstract class MovementAction : Action() {

    protected val step = 1;

    protected abstract fun layingDown(playerPosition: PlayerPosition): PlayerPosition

    protected abstract fun rolling(playerPosition: PlayerPosition): PlayerPosition

    override fun apply(state: State): State {
        return State(
            state.grid,
            if (state.playerPosition.standUp()) layingDown(state.playerPosition) else rolling(state.playerPosition),
            state
        )
    }
}


class UpAction : MovementAction() {
    override fun layingDown(playerPosition: PlayerPosition): PlayerPosition {
        val newPosition = playerPosition.up(step)
        return PlayerPosition(
            *newPosition.positions, newPosition.positions.minBy { it.x }!!.up(step)
        )
    }


    override fun rolling(playerPosition: PlayerPosition): PlayerPosition {
        return if (playerPosition.direction() == PlayerPosition.Direction.HORIZONTAL)
            playerPosition.up(step)
        else {
            PlayerPosition(playerPosition.up(step).positions.minBy { it.x }!!)
        }
    }
}

class DownAction : MovementAction() {
    override fun layingDown(playerPosition: PlayerPosition): PlayerPosition {
        val newPosition = playerPosition.down(step)
        return PlayerPosition(
            *newPosition.positions, newPosition.positions.maxBy { it.x }!!.down(step)
        )
    }


    override fun rolling(playerPosition: PlayerPosition): PlayerPosition {
        return if (playerPosition.direction() == PlayerPosition.Direction.HORIZONTAL)
            playerPosition.down(step)
        else {
            PlayerPosition(playerPosition.down(step).positions.maxBy { it.x }!!)
        }
    }
}

class LeftAction : MovementAction() {
    override fun layingDown(playerPosition: PlayerPosition): PlayerPosition {
        val newPosition = playerPosition.left(step)
        return PlayerPosition(
            *newPosition.positions, newPosition.positions.minBy { it.y }!!.left(step)
        )
    }

    override fun rolling(playerPosition: PlayerPosition): PlayerPosition {
        return if (playerPosition.direction() == PlayerPosition.Direction.HORIZONTAL) {
            PlayerPosition(playerPosition.left(step).positions.minBy { it.y }!!)
        } else
            playerPosition.left(step)
    }
}

class RightAction : MovementAction() {
    override fun layingDown(playerPosition: PlayerPosition): PlayerPosition {
        val newPosition = playerPosition.right(step)
        return PlayerPosition(
            *newPosition.positions, newPosition.positions.maxBy { it.y }!!.right(step)
        )
    }

    override fun rolling(playerPosition: PlayerPosition): PlayerPosition {
        return if (playerPosition.direction() == PlayerPosition.Direction.HORIZONTAL) {
            PlayerPosition(playerPosition.right(step).positions.maxBy { it.y }!!)
        } else
            playerPosition.right(step)
    }
}

