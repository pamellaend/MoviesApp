package com.pamella.moviesapp.data.model.cast

import com.google.gson.annotations.SerializedName

class CastResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null,
    @SerializedName("character")
    val character: String? = null
)