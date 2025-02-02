package com.smtersoyoglu.pokedex.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.smtersoyoglu.pokedex.data.mappers.mapPokemonListToDomain
import com.smtersoyoglu.pokedex.data.remote.PokeApi
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val pokeApi: PokeApi,
) : PagingSource<Int, PokedexListEntry>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokedexListEntry> {
        return try {
            val nextPage = params.key ?: 0
            val response = pokeApi.getPokemonList(limit = params.loadSize, offset = nextPage)
            val entries = mapPokemonListToDomain(response)
            Log.d("PagingSource", "Loading page: $nextPage, loadSize: ${params.loadSize}")
            LoadResult.Page(
                data = entries,
                prevKey = if (nextPage == 0) null else nextPage - params.loadSize,
                nextKey = if (entries.isEmpty()) null else nextPage + params.loadSize
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokedexListEntry>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}