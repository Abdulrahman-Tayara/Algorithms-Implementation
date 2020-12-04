package com.tayara.rolltheblock.src.core.model

abstract class Action {

    abstract fun apply(state: State): State
}