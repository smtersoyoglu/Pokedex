package com.smtersoyoglu.pokedex.presentation.pokemon_list

import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: List<PokedexListEntry> = emptyList(),
    val error: String = "",
)