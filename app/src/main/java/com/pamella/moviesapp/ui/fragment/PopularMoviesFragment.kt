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
import com.pamella.moviesapp.domain.model.Movie
import com.pamella.moviesapp.R
import com.pamella.moviesapp.ui.activitys.ErrorActivity
import com.pamella.moviesapp.ui.activitys.MovieDetailsActivity
import com.pamella.moviesapp.ui.Listener
import com.pamella.moviesapp.ui.viewmodel.MoviesViewModel
import com.pamella.moviesapp.ui.adapter.GenresRvAdapter
import com.pamella.moviesapp.ui.adapter.MoviesRvAdapter
import com.pamella.moviesapp.ui.model.ViewState
import com.pamella.moviesapp.ui.activitys.MovieDetailsActivity.Companion.MOVIE_ID

class PopularMoviesFragment : Fragment(), Listener {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        rvMovies.adapter = moviesAdapter
        rvGenres.adapter = genresAdapter

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        moviesViewModel.getPopularMovies()
        moviesViewModel.getGenres()
        progressBar = view.findViewById(R.id.loading)
        observeGenres()
        observeMovies()
        observeViewState()
    }

    override fun onResume() {
        super.onResume()
        moviesAdapter.notifyDataSetChanged()
    }

    override fun moviesByGenre(genreIds: List<Int>) {
        moviesViewModel.getMoviesByGenre(genreIds)
    }

    private fun observeMovies() {
        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun observeViewState() {
        moviesViewModel.viewStateLiveData.observe(viewLifecycleOwner) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(requireContext(), ErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun movieDetails(movieId: Int) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    private fun observeGenres() {
        moviesViewModel.genresLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun favoriteListener(movie: Movie, isChecked: Boolean) {
        if (isChecked) {
            movie.isFavorite = true
            moviesViewModel.addToFavorites(movie)
        } else {
            movie.isFavorite = false
            moviesViewModel.removeFromFavorites(movie)
        }
    }

}