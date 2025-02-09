package com.smtersoyoglu.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smtersoyoglu.pokedex.domain.model.PokedexListEntry

@Composable
fun PokemonCard(pokemon: PokedexListEntry, onClick: () -> Unit) {
    val gradientColors = listOf(
        getPokemonBackgroundColor(pokemon.number),
        getPokemonBackgroundColor(pokemon.number).copy(alpha = 0.7f)
    )

    Card(
        modifier = Modifier
            .clickable { onClick() }
            .padding(6.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .shadow(6.dp, shape = RoundedCornerShape(16.dp)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = gradientColors))
        ) {
            PokemonNumber(
                number = pokemon.number,
                modifier = Modifier.align(Alignment.TopEnd)
            )

            PokeballBackground(modifier = Modifier.align(Alignment.Center))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                PokemonImage(imageUrl = pokemon.imageUrl, name = pokemon.name)

                PokemonNameBox(name = pokemon.name)

            }
        }
    }
}

fun getPokemonBackgroundColor(number: Int): Color {
    val colors = listOf(
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFFFF9800),
        Color(0xFFE91E63)
    )
    return colors[number % colors.size]
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonCard() {
    val samplePokemon = PokedexListEntry(
        name = "Pikachu",
        imageUrl = "",
        number = 25
    )

    PokemonCard(pokemon = samplePokemon, onClick = {})
}
