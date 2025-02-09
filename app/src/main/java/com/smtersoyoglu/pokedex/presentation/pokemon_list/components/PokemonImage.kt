package com.smtersoyoglu.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

@Composable
fun PokemonImage(imageUrl: String, name: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = name,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(120.dp)
            .zIndex(1f)
            .graphicsLayer {
                rotationZ = -5f
                shape = CircleShape
                clip = true
            }
    )
}
