package com.pamella.moviesapp.data.repository

import android.net.Uri
import com.pamella.moviesapp.classes.model.*
import io.reactivex.Single

interface MoviesRepository {
    fun getPopularMovies(): Single<List<Movie>>
    fun getMovieDetails(movieId: Int): Single<MovieDetail>
    fun getAllGenres(): Single<List<Genre>>
    fun getCast(movieId: Int): Single<List<Cast>>
    fun getMoviesByGenre(genresId: String): Single<List<Movie>>
    fun getCertification(movieId: Int): Single<List<Certification>?>
    fun searchForMovie(movieSearched: Uri): Single<List<Movie>>
}