package com.smtersoyoglu.pokedex.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Generationİ(
    @SerializedName("red-blue")
    val redBlue: RedBlue,
    val yellow: Yellow
)