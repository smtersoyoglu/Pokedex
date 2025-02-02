package com.smtersoyoglu.pokedex.domain.usecase

import androidx.paging.PagingData
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import com.smtersoyoglu.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<PokedexListEntry>> {
        return repository.getPokemonList()
    }
}