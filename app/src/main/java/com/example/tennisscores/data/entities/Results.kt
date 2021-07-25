package com.example.tennisscores.data.entities


import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("rankings")
    val rankings: MutableList<Ranking>
)