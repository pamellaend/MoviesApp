package com.pamella.moviesapp.data.model.genres

import com.google.gson.annotations.SerializedName
import com.pamella.moviesapp.data.model.genres.GenreResponse

data class GenresListResponse(
    @SerializedName("genres")
    val genres: List<GenreResponse>
)
