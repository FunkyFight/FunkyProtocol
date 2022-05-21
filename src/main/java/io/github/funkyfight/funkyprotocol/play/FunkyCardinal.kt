package io.github.funkyfight.funkyprotocol.play

import net.minecraft.core.EnumDirection

enum class FunkyCardinal {

    NORTH,
    SOUTH,
    WEST,
    EAST;


    fun getOpposite(): FunkyCardinal {
        return when (this) {
            NORTH -> SOUTH
            SOUTH -> NORTH
            WEST -> EAST
            EAST -> WEST
        }
    }

    fun toEnumDirection(): EnumDirection {
        return when (this) {
            NORTH -> EnumDirection.c
            SOUTH -> EnumDirection.d
            WEST -> EnumDirection.e
            EAST -> EnumDirection.f
        }
    }

}