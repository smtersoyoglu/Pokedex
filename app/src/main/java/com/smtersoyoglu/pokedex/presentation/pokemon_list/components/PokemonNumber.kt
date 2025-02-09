package com.smtersoyoglu.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonNumber(number: Int, modifier: Modifier = Modifier) {
    Text(
        text = "#${number.toString().padStart(3, '0')}",
        color = Color.Black.copy(alpha = 0.5f),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = modifier
            .padding(top = 6.dp, end = 12.dp)
    )
}