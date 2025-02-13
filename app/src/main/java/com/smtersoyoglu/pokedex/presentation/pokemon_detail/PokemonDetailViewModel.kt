package com.smtersoyoglu.pokedex.presentation.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.smtersoyoglu.pokedex.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailState())
    val uiState: StateFlow<PokemonDetailState> = _uiState.asStateFlow()

    private val args = savedStateHandle.toRoute<Screens.PokemonDetailScreen>()

    init {
        getPokemonDetail(args.pokemonName)
    }

    private fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            _uiState.value = PokemonDetailState(isLoading = true)
            val result = getPokemonDetailUseCase(pokemonName)
            _uiState.value = when (result) {
                is Resource.Success -> {
                    PokemonDetailState(pokemonDetail = result.data)
                }
                is Resource.Error -> {
                    PokemonDetailState(error = result.message ?: "Unknown error")
                }
            }

        }
    }
}