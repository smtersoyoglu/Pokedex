package com.smtersoyoglu.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.smtersoyoglu.pokedex.common.Constants.PAGE_SIZE
import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.data.mappers.mapResultToEntry
import com.smtersoyoglu.pokedex.data.paging.PokemonPagingSource
import com.smtersoyoglu.pokedex.data.remote.PokeApi
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import com.smtersoyoglu.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
) : PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<PokedexListEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(api) }
        ).flow
    }


    // Repository'de güncelleme
    override suspend fun searchPokemon(query: String): Resource<List<PokedexListEntry>> {
        return try {
            val allPokemon = api.getPokemonList(limit = 1000, offset = 0) // Tüm listeyi çek
            val filtered = allPokemon.results
                .filter { it.name.contains(query, ignoreCase = true) || it.url.split("/").last { it.isNotEmpty() } == query }
                .map { mapResultToEntry(it) }
            Resource.Success(filtered)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Search failed")
        }
    }


}