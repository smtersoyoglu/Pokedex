package com.smtersoyoglu.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PokeballBackground(modifier: Modifier) {
    Canvas(
        modifier = modifier.size(120.dp)
    ) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2

        // Üst yarım kırmızı, alt yarım beyaz
        drawArc(Color.Red.copy(alpha = 0.2f), 180f, 180f, useCenter = true, size = size)
        drawArc(Color.White.copy(alpha = 0.2f), 0f, 180f, useCenter = true, size = size)

        // Ortadaki siyah çizgi
        drawLine(Color.Black.copy(alpha = 0.3f), Offset(0f, center.y), Offset(size.width, center.y), strokeWidth = 8f)

        // Orta beyaz çember
        drawCircle(Color.White.copy(alpha = 0.5f), radius / 3, center, style = Stroke(width = 5f))

        // İç siyah daire
        drawCircle(Color.Black.copy(alpha = 0.5f), radius / 3 - 3f, center)
    }
}