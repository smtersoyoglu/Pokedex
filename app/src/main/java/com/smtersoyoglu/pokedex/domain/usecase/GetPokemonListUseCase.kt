package com.smtersoyoglu.pokedex.domain.usecase

import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import com.smtersoyoglu.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(limit: Int, offset: Int): Flow<Resource<List<PokedexListEntry>>> {
        return repository.getPokemonList(limit, offset)
    }
}