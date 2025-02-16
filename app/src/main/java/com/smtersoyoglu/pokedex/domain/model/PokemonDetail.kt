package com.smtersoyoglu.pokedex.domain.model

import androidx.compose.ui.graphics.Color

data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<TypeWithColor>,
    val height: Float,
    val weight: Float,
    val stats: Map<StatType, Int>,
    val imageUrl: String,
)

data class TypeWithColor(
    val name: String,
    val color: Color,
)

enum class StatType(val value: String) {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed");

    companion object {
        fun fromValue(value: String): StatType {
            return entries.firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("Invalid stat type: $value")
        }
    }
}

