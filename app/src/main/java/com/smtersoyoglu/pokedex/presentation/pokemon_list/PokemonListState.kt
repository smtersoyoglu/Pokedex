package com.smtersoyoglu.pokedex.presentation.pokemon_list

import androidx.paging.PagingData
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import kotlinx.coroutines.flow.Flow

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: Flow<PagingData<PokedexListEntry>>? = null,
    val searchResults: List<PokedexListEntry> = emptyList(),
    val error: String = "",
    val searchQuery: String = ""
)