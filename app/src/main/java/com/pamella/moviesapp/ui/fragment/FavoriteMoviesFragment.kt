package com.pamella.moviesapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pamella.moviesapp.R
import com.pamella.moviesapp.domain.model.Movie
import com.pamella.moviesapp.ui.Listener
import com.pamella.moviesapp.ui.viewmodel.MoviesViewModel
import com.pamella.moviesapp.ui.adapter.GenresRvAdapter
import com.pamella.moviesapp.ui.adapter.MoviesRvAdapter
import com.pamella.moviesapp.ui.activitys.MovieDetailsActivity
import com.pamella.moviesapp.ui.activitys.MovieDetailsActivity.Companion.MOVIE_ID

class FavoriteMoviesFragment : Fragment(), Listener {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var rvGenres: RecyclerView
    private lateinit var rvMovies: RecyclerView
    private lateinit var viewModelFavorites: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvGenres = view.findViewById(R.id.rvGenres)
        rvMovies = view.findViewById(R.id.rvMovies)

        progressBar = view.findViewById(R.id.loading)

        viewModelFavorites = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        rvGenres.adapter = genresAdapter
        rvMovies.adapter = moviesAdapter

        viewModelFavorites.getGenres()
        observeGenres()
        observeFavoriteMovies()
    }

    override fun onResume() {
        super.onResume()
        viewModelFavorites.getFavoriteMovies()
    }

    private fun observeGenres() {
        viewModelFavorites.genresLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun observeFavoriteMovies() {
        viewModelFavorites.favoriteMoviesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun favoriteListener(movie: Movie, isChecked: Boolean) {
        if (!isChecked) {
            movie.isFavorite = false
            viewModelFavorites.removeFromFavorites(movie)
        }
    }

    override fun movieDetails(movieId: Int) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    override fun moviesByGenre(genreIds: List<Int>) {
        viewModelFavorites.favoriteMoviesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let { movies ->
                val movieList = mutableListOf<Movie>()
                movies.forEach { movie ->
                    if (movie.genreIds.containsAll(genreIds)) {
                        movieList.add(movie)
                    }
                }
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(movieList)
                moviesAdapter.notifyDataSetChanged()
            }
        }
    }


}