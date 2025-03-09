package com.smtersoyoglu.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.smtersoyoglu.pokedex.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListState())
    val uiState: StateFlow<PokemonListState> = _uiState.asStateFlow()

    init {
        getPokemonList()
    }


    private fun getPokemonList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val pokemonFlow = getPokemonListUseCase().cachedIn(viewModelScope)
                _uiState.update { it.copy(pokemonList = pokemonFlow, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Unknown error", isLoading = false) }
            }
        }
    }
}