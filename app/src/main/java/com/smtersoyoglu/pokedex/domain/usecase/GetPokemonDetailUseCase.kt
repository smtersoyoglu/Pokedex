package com.smtersoyoglu.pokedex.domain.usecase

import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.domain.model.PokemonDetail
import com.smtersoyoglu.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(pokemonName: String): Resource<PokemonDetail> {
        return repository.getPokemonInfo(pokemonName)
    }

}