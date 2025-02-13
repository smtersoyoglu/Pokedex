package com.smtersoyoglu.pokedex.presentation.pokemon_detail

import com.smtersoyoglu.pokedex.domain.model.PokemonDetail

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val pokemonDetail: PokemonDetail? = null,
    val error: String = ""
)