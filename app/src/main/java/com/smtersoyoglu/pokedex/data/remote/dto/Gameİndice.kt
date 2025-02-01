package com.smtersoyoglu.pokedex.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Gameİndice(
    @SerializedName("game_index")
    val gameIndex: Int,
    val version: Version
)