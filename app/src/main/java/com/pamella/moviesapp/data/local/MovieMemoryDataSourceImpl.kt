package com.pamella.moviesapp.data.local

import com.pamella.moviesapp.data.model.movies.MovieResponse
import io.reactivex.Single
import java.lang.IllegalStateException

object MovieMemoryDataSourceImpl : MovieLocalDataSource {

    private val favoriteMoviesList = mutableListOf<MovieResponse>()

    override fun getFavoriteMovies(): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            emitter.onSuccess(favoriteMoviesList)
        }
    }

    override fun addToFavorites(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            val result = favoriteMoviesList.add(movie) //result is either true or false
            if (result) {
                emitter.onSuccess(favoriteMoviesList)
            } else {
                emitter.onError(IllegalStateException())
            }
        }
    }

    override fun removeFromFavorites(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            val movieToRemove = favoriteMoviesList.find {
                it.id == movie.id
            }
            val result = favoriteMoviesList.remove(movieToRemove)
            if (result) {
                emitter.onSuccess(favoriteMoviesList)
            } else {
                emitter.onError(IllegalStateException())
            }
        }
    }



}