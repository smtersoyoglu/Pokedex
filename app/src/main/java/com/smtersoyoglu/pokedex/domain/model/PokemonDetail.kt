package com.smtersoyoglu.pokedex.domain.model

import androidx.annotation.ColorRes

data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<TypeWithColor>,
    val height: Float,
    val weight: Float,
    val stats: Map<StatType, Int>,
    val imageUrl: String
)

enum class StatType(val value: String) {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed"),
    EXP("experience");

    companion object {
        fun fromValue(value: String): StatType {
            return values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("Invalid stat type: $value")
        }
    }
}



data class TypeWithColor(
    val name: String,
    @ColorRes val colorRes: Int
)
