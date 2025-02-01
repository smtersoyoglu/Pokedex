package com.smtersoyoglu.pokedex.data.repository

import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.data.mappers.mapPokemonListToDomain
import com.smtersoyoglu.pokedex.data.remote.PokeApi
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry
import com.smtersoyoglu.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
) : PokemonRepository {

    override fun getPokemonList(limit: Int, offset: Int): Flow<Resource<List<PokedexListEntry>>> = flow {
        try {
            val response = api.getPokemonList(limit, offset)
            val mappedList = mapPokemonListToDomain(response)
            emit(Resource.Success(mappedList))
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection."))
        }
    }


}