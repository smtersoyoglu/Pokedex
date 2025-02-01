package com.smtersoyoglu.pokedex.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Generationİii(
    val emerald: Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphire
)