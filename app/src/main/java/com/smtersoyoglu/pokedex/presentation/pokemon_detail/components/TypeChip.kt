package com.smtersoyoglu.pokedex.presentation.pokemon_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smtersoyoglu.pokedex.domain.model.TypeWithColor
import com.smtersoyoglu.pokedex.ui.theme.righteous_regular

@Composable
fun TypeChip(type: TypeWithColor) {
    Box(
        modifier = Modifier
            .background(
                color = type.color,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = type.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.labelMedium.copy(
                color = androidx.compose.ui.graphics.Color.White,
                fontFamily = righteous_regular,
                fontSize = 18.sp
            )
        )
    }
}
