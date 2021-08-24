package com.pamella.moviesapp.domain.usecase

import android.net.Uri
import com.pamella.moviesapp.data.repository.MoviesRepository

class SearchForMovie(private val moviesRepository: MoviesRepository = MoviesRepository()) {
    fun executeSearch(movieSearched: Uri) = moviesRepository.searchForMovie(movieSearched)
}