package com.smtersoyoglu.pokedex.presentation.pokemon_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.smtersoyoglu.pokedex.R
import com.smtersoyoglu.pokedex.navigation.Screens
import com.smtersoyoglu.pokedex.presentation.pokemon_list.components.PokemonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val pokemonItems = uiState.pokemonList?.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            TopAppBar(
                title = { Text("Pokedex", color = Color.White) },
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.pokeball),
                        contentDescription = "Pokeball Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE63946))
            )

            pokemonItems?.let { pagingItems ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(pagingItems.itemCount) { index ->
                        pagingItems[index]?.let { pokemon ->
                            PokemonCard(
                                pokemon = pokemon,
                                onClick = {
                                    navController.navigate(Screens.PokemonDetailScreen(pokemon.name))
                                }
                            )
                        }
                    }
                    // Paging load state gösterimleri
                    pagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }

                            loadState.append is LoadState.Loading -> {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.BottomCenter
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }

                            loadState.refresh is LoadState.Error -> {
                                val e = loadState.refresh as LoadState.Error
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Error: ${e.error.localizedMessage}",
                                            color = Color.Red,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }

                            loadState.append is LoadState.Error -> {
                                val e = loadState.append as LoadState.Error
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Error: ${e.error.localizedMessage}",
                                            color = Color.Red,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
