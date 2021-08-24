package com.pamella.moviesapp.ui

import com.pamella.moviesapp.domain.model.Movie

interface Listener {
    fun movieDetails(movieId: Int)
    fun moviesByGenre(genreIds: List<Int>)
    fun favoriteListener(movie: Movie, isChecked: Boolean)
}