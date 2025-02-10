package com.smtersoyoglu.pokedex.data.mappers

import com.smtersoyoglu.pokedex.R
import com.smtersoyoglu.pokedex.data.remote.dto.Pokemon
import com.smtersoyoglu.pokedex.data.remote.dto.Type
import com.smtersoyoglu.pokedex.domain.model.PokemonDetail
import com.smtersoyoglu.pokedex.domain.model.StatType
import com.smtersoyoglu.pokedex.domain.model.TypeWithColor

fun Pokemon.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = types.map { it.toTypeWithColor() },
        height = height / 10f, // Metreye çeviriyoruz
        weight = weight / 10f, // Kilograma çeviriyoruz
        stats = stats.associate { 
            StatType.fromValue(it.stat.name.lowercase()) to it.baseStat
        },
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}

fun Type.toTypeWithColor(): TypeWithColor {
    val color = when (type.name.lowercase()) {
        "fire" -> R.color.fire
        "water" -> R.color.water
        "grass" -> R.color.grass
        else -> R.color.default_type
    }
    return TypeWithColor(
        name = type.name.replaceFirstChar { it.uppercase() },
        colorRes = color
    )
}
