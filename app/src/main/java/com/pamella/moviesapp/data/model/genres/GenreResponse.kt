package com.pamella.moviesapp.data.model.genres

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val genreName: String
)
