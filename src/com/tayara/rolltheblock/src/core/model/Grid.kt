package com.tayara.rolltheblock.src.core.model

data class Grid<T>(val rows: Int, val columns: Int) {

    private val data: MutableList<MutableList<T?>> = ArrayList()


    init {
        for (i in 0 until rows) {
            data.add(i, ArrayList())
            for (j in 0 until columns)
                data[i].add(j, null)
        }
    }

    operator fun get(row: Int, column: Int) = data[row][column]

    operator fun set(row: Int, column: Int, value: T) {
        data[row][column] = value
    }
}
