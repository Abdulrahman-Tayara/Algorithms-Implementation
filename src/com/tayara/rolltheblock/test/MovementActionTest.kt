package com.tayara.rolltheblock.test

import com.tayara.rolltheblock.src.core.action.UpAction
import com.tayara.rolltheblock.src.core.model.*

fun main() {
    moveUpTest()
}

fun moveUpTest() {

    val s = State(Grid(3, 3), PlayerPosition(Position2D(3, 3)))
    val s2 = State(Grid(3, 3), PlayerPosition(Position2D(3, 3)))

    print(s == s2)

}