package com.smtersoyoglu.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smtersoyoglu.pokedex.common.Resource
import com.smtersoyoglu.pokedex.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListState())
    val uiState: StateFlow<PokemonListState> = _uiState.asStateFlow()

    init {
        getPokemonList()
    }

    private fun getPokemonList(limit: Int = 20, offset: Int = 0) {
        getPokemonListUseCase(limit, offset)
            .onStart { _uiState.update { it.copy(isLoading = true) } }
            .onCompletion { _uiState.update { it.copy(isLoading = false) } }
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> _uiState.update {
                        it.copy(
                            pokemonList = resource.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> _uiState.update {
                        it.copy(
                            error = resource.message ?: "Unknown error"
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}