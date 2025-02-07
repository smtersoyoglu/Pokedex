package com.smtersoyoglu.pokedex.domain.repository

import androidx.paging.PagingData
import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList() : Flow<PagingData<PokedexListEntry>>


    suspend fun searchPokemon(query: String): Resource<List<PokedexListEntry>>

}