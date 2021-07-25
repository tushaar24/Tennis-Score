package com.example.tennisscores.data.entities


import com.google.gson.annotations.SerializedName

data class AtpRanking(
    @SerializedName("results")
    val results: Results
)