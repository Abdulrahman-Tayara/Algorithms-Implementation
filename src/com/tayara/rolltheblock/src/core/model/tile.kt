package com.tayara.rolltheblock.src.core.model

sealed class Tile

object Hole : Tile()
object Land : Tile()
object Space : Tile()
