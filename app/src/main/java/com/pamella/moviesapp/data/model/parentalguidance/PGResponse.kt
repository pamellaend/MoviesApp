package com.pamella.moviesapp.data.model.parentalguidance

import com.google.gson.annotations.SerializedName

class PGResponse(
    @SerializedName("pg")
    val certification: String,
    @SerializedName("type")
    val type: Int
)