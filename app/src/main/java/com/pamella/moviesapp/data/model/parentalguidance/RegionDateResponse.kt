package com.pamella.moviesapp.data.model.parentalguidance

import com.google.gson.annotations.SerializedName

class RegionDateResponse(
    @SerializedName("iso_3166_1")
    val region: String,
    @SerializedName("release_dates")
    val releaseDates: List<PGResponse>
)