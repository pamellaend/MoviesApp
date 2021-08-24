package com.pamella.moviesapp.data.repository

import android.net.Uri
import com.pamella.moviesapp.data.foundation.Network
import com.pamella.moviesapp.data.local.MovieLocalDataSourceImpl
import com.pamella.moviesapp.data.remote.MoviesRemoteSource
import com.pamella.moviesapp.domain.model.*
import com.pamella.moviesapp.data.map.*
import io.reactivex.Single

class MoviesRepository {
    private val moviesRemoteSource: MoviesRemoteSource = Network.remoteSource()
    private val movieLocalDataSource = MovieLocalDataSourceImpl()
    private val movieMapper = MovieMapper()
    private val genreMapper = GenreMapper()
    private val castMapper = CastMapper()
    private val movieDetailMapper = MovieDetailMapper()
    private val certificationMapper = CertificationMapper()

    fun getPopMovies(): Single<List<Movie>> {
        return moviesRemoteSource
            .getPopularMovies()
            .map {
                movieMapper.map(it.movieResults)
            }
    }

    fun getMovieDetails(movieId: Int): Single<MovieDetail> {
        return moviesRemoteSource
            .getMovieDetails(movieId)
            .map {
                movieDetailMapper.map(it)
            }
    }

    fun getAllGenres(): Single<List<Genre>> {
        return moviesRemoteSource
            .getAllGenres()
            .map {
                genreMapper.map(it.genres)
            }
    }

    fun getMoviesByGenre(genresId: String): Single<List<Movie>> {
        return moviesRemoteSource
            .getMoviesByGenre(genresId)
            .map {
                movieMapper.map(it.movieResults)
            }
    }

    fun getCast(movieId: Int): Single<List<Cast>> {
        return moviesRemoteSource
            .getCast(movieId)
            .map {
                castMapper.map(it.cast)
            }
    }

    fun getCertification(movieId: Int): Single<List<ParentalGuidance>?> {
        return moviesRemoteSource
            .getCertification(movieId)
            .map {
                val br = it.results.find { certificationResponse ->
                    certificationResponse.region == BR
                }
                certificationMapper.map(br?.releaseDates)
            }
    }

    fun searchForMovie(movieSearched: Uri): Single<List<Movie>> {
        return moviesRemoteSource
            .searchForMovie(movieSearched)
            .map {
                movieMapper.map(it.movieResults)
            }
    }

    companion object {
        private const val BR = "BR"
    }

}