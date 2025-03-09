package com.smtersoyoglu.pokedex.data.mappers

import com.smtersoyoglu.pokedex.data.remote.dto.PokemonList
import com.smtersoyoglu.pokedex.data.remote.dto.Result
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry

fun mapResultToEntry(result: Result): PokedexListEntry {
    val number = extractNumberFromUrl(result.url)
    return PokedexListEntry(
        name = result.name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${number}.png",
        number = number
    )
}

// PokemonList DTO -> Domain List
fun mapPokemonListToDomain(dto: PokemonList): List<PokedexListEntry> {
    return dto.results.map { result ->
        mapResultToEntry(result)
    }
}

// URL'den ID çıkaran yardımcı fonksiyon
private fun extractNumberFromUrl(url: String): Int {
    return url.split("/")
        .filter { it.isNotBlank() }
        .lastOrNull { it.toIntOrNull() != null }
        ?.toIntOrNull() ?: 0
}