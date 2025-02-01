package com.smtersoyoglu.pokedex.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {

    @Serializable
    data object PokemonListScreen : Screens

}