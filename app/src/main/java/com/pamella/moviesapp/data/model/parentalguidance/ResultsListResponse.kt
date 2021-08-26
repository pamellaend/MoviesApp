package com.pamella.moviesapp.data.model.parentalguidance

import com.google.gson.annotations.SerializedName

class ResultsListResponse(@SerializedName("results") val results: List<RegionDateResponse>)