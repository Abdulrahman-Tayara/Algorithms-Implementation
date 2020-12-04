package com.tayara.rolltheblock.src.presentation.gameplay

import com.tayara.rolltheblock.src.core.model.State

abstract class StateGenerator {

    abstract fun generate(): State
}