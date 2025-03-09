package com.smtersoyoglu.pokedex.presentation.pokemon_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.smtersoyoglu.pokedex.R
import com.smtersoyoglu.pokedex.common.extractDominantColor
import com.smtersoyoglu.pokedex.presentation.pokemon_detail.components.ErrorMessage
import com.smtersoyoglu.pokedex.presentation.pokemon_detail.components.FullScreenLoader
import com.smtersoyoglu.pokedex.presentation.pokemon_detail.components.InfoItem
import com.smtersoyoglu.pokedex.presentation.pokemon_detail.components.AnimatedStatBar
import com.smtersoyoglu.pokedex.presentation.pokemon_detail.components.TypeChip
import com.smtersoyoglu.pokedex.presentation.pokemon_list.components.PokeballBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonDetailScreen(
    navController: NavController,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val dominantColor = remember { mutableStateOf(Color.Gray) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dominantColor.value)
    ) {
        when {
            uiState.isLoading -> FullScreenLoader()
            uiState.error.isNotBlank() -> ErrorMessage(message = uiState.error)
            else -> uiState.pokemonDetail?.let { pokemon ->
                Column(modifier = Modifier.fillMaxSize()) {

                    Spacer(modifier = Modifier.height(88.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {
                        PokeballBackground(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(280.dp)
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokemon.imageUrl)
                                .crossfade(true)
                                .allowHardware(false)
                                .listener(
                                    onSuccess = { _, result ->
                                        result.drawable.toBitmap().extractDominantColor { color ->
                                            dominantColor.value = color
                                        }
                                    }
                                )
                                .build(),
                            contentDescription = pokemon.name,
                            modifier = Modifier
                                .size(250.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 34.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // İsim ve ID
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                                    style = MaterialTheme.typography.headlineLarge.copy(
                                        color = Color.Black
                                    )
                                )
                                Text(
                                    text = "#${pokemon.id.toString().padStart(3, '0')}",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Türler
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                pokemon.types.forEachIndexed { index, type ->
                                    TypeChip(type = type)
                                    if (index != pokemon.types.lastIndex) {
                                        Spacer(modifier = Modifier.width(8.dp))
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            // Weight & Height
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                InfoItem(
                                    value = "${pokemon.weight} KG",
                                    title = "Weight",
                                    icon = R.drawable.weight
                                )
                                InfoItem(
                                    value = "${pokemon.height} M",
                                    title = "Height",
                                    icon = R.drawable.height
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            // Stats
                            Text(
                                text = "Base Stats",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = Color.Black
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                pokemon.stats.forEach { (stat, value) ->
                                    AnimatedStatBar(
                                        stat = stat,
                                        value = value,
                                        maxValue = 300,
                                        color = dominantColor.value
                                    )
                                }
                            }
                        }
                    }
                }

                // Floating Back Button
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(top = 44.dp, start = 16.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
