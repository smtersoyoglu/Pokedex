package com.smtersoyoglu.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.smtersoyoglu.pokedex.domain.usecase.GetPokemonListUseCase
import com.smtersoyoglu.pokedex.domain.usecase.SearchPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListState())
    val uiState: StateFlow<PokemonListState> = _uiState.asStateFlow()

    init {
        getPokemonList()
    }

    private var searchJob: Job? = null

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (query.isNotEmpty()) {
                delay(300) // Debounce 300ms
                searchPokemon(query)
            } else {
                _uiState.update { it.copy(searchResults = emptyList()) }
            }
        }
    }

    private fun searchPokemon(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val results = searchPokemonUseCase(query)
                _uiState.update {
                    it.copy(
                        searchResults = results.data ?: emptyList(),
                        isLoading = false,
                        error = if (results.data.isNullOrEmpty()) "No results found" else ""
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Search failed",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "", searchResults = emptyList()) }
            try {
                val pokemonFlow = getPokemonListUseCase().cachedIn(viewModelScope)
                _uiState.update { it.copy(pokemonList = pokemonFlow, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Unknown error", isLoading = false) }
            }
        }
    }


}