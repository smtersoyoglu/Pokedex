package com.smtersoyoglu.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonNameBox(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(
                color = Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Text(
            text = name.replaceFirstChar { it.uppercase() },
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            style = TextStyle(
                shadow = Shadow(Color.Black.copy(alpha = 0.3f), Offset(2f, 2f), blurRadius = 4f)
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}