package com.smtersoyoglu.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smtersoyoglu.pokedex.presentation.pokemon_list.PokemonListScreen

@Composable
fun PokemonNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.PokemonListScreen
    ) {

        composable <Screens.PokemonListScreen> {
            PokemonListScreen(navController = navController)
        }

    }
}