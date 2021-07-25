package com.example.tennisscores.data.entities


import com.google.gson.annotations.SerializedName

data class Ranking(
    @SerializedName("country")
    val country: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("movement")
    val movement: String,
    @SerializedName("ranking")
    val ranking: Int,
    @SerializedName("ranking_points")
    val rankingPoints: Int
)