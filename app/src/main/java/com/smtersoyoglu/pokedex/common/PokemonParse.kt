package com.smtersoyoglu.pokedex.common

import androidx.compose.ui.graphics.Color

object TypeColors {
    fun getColorForType(type: String): Color {
        return when (type.lowercase()) {
            "fighting" -> Color(0xFFC22E28)
            "flying" -> Color(0xFFA890F0)
            "poison" -> Color(0xFFA040A0)
            "ground" -> Color(0xFFE2BF65)
            "rock" -> Color(0xFFB6A136)
            "bug" -> Color(0xFFA8B820)
            "ghost" -> Color(0xFF735797)
            "steel" -> Color(0xFFB7B7CE)
            "fire" -> Color(0xFFCC0000)
            "water" -> Color(0xFF6390F0)
            "grass" -> Color(0xFF7AC74C)
            "electric" -> Color(0xFFF7D02C)
            "psychic" -> Color(0xFFF95587)
            "ice" -> Color(0xFF96D9D6)
            "dragon" -> Color(0xFF6F35FC)
            "fairy" -> Color(0xFFD685AD)
            "dark" -> Color(0xFF484848)
            else -> Color(0xFFA8A77A)
        }
    }
}

