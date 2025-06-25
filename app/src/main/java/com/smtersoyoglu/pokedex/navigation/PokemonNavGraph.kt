package com.smtersoyoglu.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smtersoyoglu.pokedex.navigation.Screens.SplashScreen
import com.smtersoyoglu.pokedex.navigation.Screens.PokemonListScreen
import com.smtersoyoglu.pokedex.navigation.Screens.PokemonDetailScreen
import com.smtersoyoglu.pokedex.presentation.pokemon_detail.PokemonDetailScreen
import com.smtersoyoglu.pokedex.presentation.pokemon_list.PokemonListScreen
import com.smtersoyoglu.pokedex.presentation.splash_screen.SplashScreen

@Composable
fun PokemonNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreen
    ) {

        composable <SplashScreen> {
            SplashScreen(
                onNavigate = {
                    navController.navigate(PokemonListScreen) {
                        popUpTo(SplashScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable <PokemonListScreen> {
            PokemonListScreen(
                onNavigateToDetail = { pokemonName ->
                    navController.navigate(PokemonDetailScreen(pokemonName))
                }
            )
        }

        composable <PokemonDetailScreen> {
            PokemonDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}