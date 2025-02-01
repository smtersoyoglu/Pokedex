package com.smtersoyoglu.pokedex.domain.repository

import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(limit: Int, offset: Int): Flow<Resource<List<PokedexListEntry>>>

}