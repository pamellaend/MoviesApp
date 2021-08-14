package com.pamella.moviesapp.data.remotesource

import android.net.Uri
import com.pamella.moviesapp.data.model.cast.CastListResponse
import com.pamella.moviesapp.data.model.certification.CertificationListReponse
import com.pamella.moviesapp.data.model.genres.GenresListResponse
import com.pamella.moviesapp.data.model.movies.MovieDetailResponse
import com.pamella.moviesapp.data.model.movies.MoviesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteSource {

    @GET("movie/popular")
    fun getPopularMovies(): Single<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<MovieDetailResponse>

    @GET("search/movie")
    fun searchForMovie(@Query("query") movieSearched: Uri): Single<MoviesListResponse>

    @GET("movie/{movie_id}/credits")
    fun getCast(@Path("movie_id") movieId: Int): Single<CastListResponse>

    @GET("genre/movie/list")
    fun getAllGenres(): Single<GenresListResponse>

    @GET("movie/{movie_id}/release_dates")
    fun getCertification(@Path("movie_id") movieId: Int): Single<CertificationListReponse>

    @GET("discover/movie")
    fun getMoviesByGenre(@Query("with_genres", encoded = true) genresId: String): Single<MoviesListResponse>
}