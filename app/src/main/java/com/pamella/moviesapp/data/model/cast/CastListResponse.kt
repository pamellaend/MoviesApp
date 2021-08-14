package com.pamella.moviesapp.data.model.cast

import com.google.gson.annotations.SerializedName

class CastListResponse(
    @SerializedName("cast")
    val cast: List<CastResponse>
)