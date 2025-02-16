package com.smtersoyoglu.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.smtersoyoglu.pokedex.common.extractDominantColor

@Composable
fun PokemonImage(
    imageUrl: String,
    name: String,
    onColorExtracted: (Color) -> Unit
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .allowHardware(false)
            .listener(
                onSuccess = { _, result ->
                    // Bitmap extraction: resmin bitmap'ini alıp dominant renk çıkarma
                    val bitmap = result.drawable.toBitmap()
                    bitmap.extractDominantColor { color ->
                        onColorExtracted(color)
                    }
                }
            )
            .build(),
        contentDescription = name,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(120.dp)
            .graphicsLayer {
                rotationZ = -5f
                shape = CircleShape
                clip = true
            }
    )
}
