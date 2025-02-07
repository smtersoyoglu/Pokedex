package com.smtersoyoglu.pokedex.domain.usecase

import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import com.smtersoyoglu.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(query: String): Resource<List<PokedexListEntry>> {
        return repository.searchPokemon(query)
    }
}