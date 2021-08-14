package com.pamella.moviesapp.activitys

import com.pamella.moviesapp.classes.model.Movie

interface ClickListener {
    fun openMovieDetails(movieId: Int)
    fun loadMoviesWithGenre(genreIds: List<Int>)
    fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean)
}