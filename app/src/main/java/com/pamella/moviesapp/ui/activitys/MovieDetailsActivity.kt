package com.pamella.moviesapp.ui.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pamella.moviesapp.data.foundation.Constants
import com.pamella.moviesapp.domain.model.MovieDetail
import com.pamella.moviesapp.R
import com.pamella.moviesapp.ui.adapter.CastRvAdapter
import com.pamella.moviesapp.ui.adapter.MovieDetailsGenresRvAdapter
import com.pamella.moviesapp.ui.model.ViewState
import com.pamella.moviesapp.ui.viewmodel.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var returnBtn: View
    private lateinit var posterMovie: ImageView
    private lateinit var movieRating: TextView
    private lateinit var movieTitle: TextView
    private lateinit var movieYear: TextView
    private lateinit var ageRestriction: TextView
    private lateinit var movieDuration: TextView
    private lateinit var favButton: ImageView
    private lateinit var genresRv: RecyclerView
    private lateinit var movieSynopsis: TextView
    private lateinit var castRv: RecyclerView
    private lateinit var castRvAdapter: CastRvAdapter
    private lateinit var genresRvAdapter: MovieDetailsGenresRvAdapter
    private val viewModel = MovieDetailsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)
        posterMovie = findViewById(R.id.posterMovie)
        movieTitle = findViewById(R.id.movieTitle)
        movieRating = findViewById(R.id.ratingMovieInfoAct)
        movieYear = findViewById(R.id.movieYear)
        ageRestriction = findViewById(R.id.parentalGuidance)
        movieDuration = findViewById(R.id.movieDuration)
        favButton = findViewById(R.id.favBtnMovieInfo)
        genresRv = findViewById(R.id.genreRvMovieInfo)
        movieSynopsis = findViewById(R.id.movieSynopsis)
        castRv = findViewById(R.id.castRv)
        returnBtn = findViewById(R.id.returnBtn)

        val movieId = intent.extras?.getInt(MOVIE_ID)

        viewModel.getMovieDetails(movieId!!)
        observeMovieDetails()
        observeViewState()

        returnBtn.setOnClickListener { finish() }
    }


    private fun showMovie(movie: MovieDetail) {
        genresRvAdapter = MovieDetailsGenresRvAdapter(movie.genres)
        genresRv.adapter = genresRvAdapter

        castRvAdapter = CastRvAdapter(this)
        castRv.adapter = castRvAdapter

        viewModel.getCast(movie.id)
        observeCastOfMovie()

        movie.backdropPath?.let {
            Glide.with(this).load(Constants.BASE_URL_IMAGE.value + movie.backdropPath).into(posterMovie)
        }
        movie.overview?.let {
            movieSynopsis.text = it
        }
        movie.runtime?.let {
            movieDuration.text = movie.getRuntime()
        }

        movieTitle.text = movie.title
        movieRating.text = movie.getRating()

        if (movie.isFavorite) {
            favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        movieYear.text = movie.getReleaseYear()

        viewModel.getCertification(movie.id)
        observeCertification()
    }

    private fun observeMovieDetails() {
        viewModel.movieLiveData.observe(this) { result ->
            result?.let {
                showMovie(it)
            }
        }
    }

    private fun observeViewState() {
        viewModel.viewStateLiveData.observe(this) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(this, ErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun observeCastOfMovie() {
        viewModel.castLiveData.observe(this) { result ->
            result?.let {
                castRvAdapter.dataset.addAll(it)
                castRvAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun observeCertification() {
        viewModel.certificationLiveData.observe(this) { result ->
            result?.let {
                it.forEach { parental_guidance ->
                    ageRestriction.text = ("PG-" + parental_guidance.pg)
                }
            }
        }
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }
}
