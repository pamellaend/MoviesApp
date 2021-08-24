package com.pamella.moviesapp.data.model.parentalguidance

import com.google.gson.annotations.SerializedName

class ReleaseDatesResponse(
    @SerializedName("pg")
    val certification: String,
    @SerializedName("type")
    val type: Int
)