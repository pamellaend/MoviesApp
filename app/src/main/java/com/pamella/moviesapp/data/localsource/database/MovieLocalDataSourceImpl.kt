package com.pamella.moviesapp.data.localsource.database

import com.pamella.moviesapp.data.localsource.MovieLocalDataSource
import com.pamella.moviesapp.data.map.MovieDataMapper
import com.pamella.moviesapp.data.map.MovieResponseMapper
import com.pamella.moviesapp.data.model.movies.MovieResponse
import io.reactivex.Single
import java.lang.IllegalStateException

class MovieLocalDataSourceImpl : MovieLocalDataSource {
    private val dao = AppDatabaseProvider.getFavoriteMovieDao()
    private val movieDataMapper = MovieDataMapper()
    private val movieResponseMapper = MovieResponseMapper()

    override fun getFavoriteMovies(): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            dao?.let {
                val favoriteMovies = dao.getAll()
                emitter.onSuccess(favoriteMovies.map())
            } ?: emitter.onError(IllegalStateException())
        }
    }

    override fun addToFavorites(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            dao?.let {
                val mappedMovie = movieDataMapper.map(movie)
                dao.insert(mappedMovie)
                val favoriteMovies = dao.getAll()
                emitter.onSuccess(favoriteMovies.map())
            } ?: emitter.onError(IllegalStateException())
        }
    }

    override fun removeFromFavorites(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            dao?.let {
                val mappedMovie = movieDataMapper.map(movie)
                dao.delete(mappedMovie)
                val favoriteMovies = dao.getAll()
                emitter.onSuccess(favoriteMovies.map())
            } ?: emitter.onError(IllegalStateException())
        }
    }


    private fun List<MovieData>.map(): List<MovieResponse> {
        return this.map { movie ->
            movieResponseMapper.map(movie)
        }
    }

}