package com.pamella.moviesapp.classes.usecase

import android.net.Uri
import com.pamella.moviesapp.data.repository.MoviesRepository
import com.pamella.moviesapp.data.repository.MoviesRepositoryImpl

class SearchForMovie(private val moviesRepository: MoviesRepository = MoviesRepositoryImpl()) {
    fun executeSearch(movieSearched: Uri) = moviesRepository.searchForMovie(movieSearched)
}