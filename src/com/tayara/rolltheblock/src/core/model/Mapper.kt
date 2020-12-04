package com.tayara.rolltheblock.src.core.model

abstract class Mapper<FROM, TO> {

    abstract fun map(model: FROM): TO

    abstract fun unmap(model: TO): FROM

}