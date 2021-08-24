package com.pamella.moviesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pamella.moviesapp.R
import com.pamella.moviesapp.data.foundation.Constants
import com.pamella.moviesapp.domain.model.Movie
import com.pamella.moviesapp.ui.Listener

class MoviesRvAdapter(
    val context: Context,
    private val listener: Listener? = null,
    var dataset: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<MoviesRvAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgMovie: ImageView? = view.findViewById(R.id.imgMovie)
        var titleMovie: TextView? = view.findViewById(R.id.titleMovie)
        var rateMovie: TextView? = view.findViewById(R.id.rateMovie)
        var favBtn: ToggleButton? = view.findViewById(R.id.favBtn)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataset[position].imgHome !== "") {
            holder.imgMovie?.let {
                Glide.with(context).load(Constants.BASE_URL_IMAGE.value + dataset[position].imgHome).into(it)
            }
        }
        holder.titleMovie?.text = dataset[position].title
        holder.rateMovie?.text = dataset[position].getRating()

        holder.imgMovie?.setOnClickListener {
            listener?.movieDetails(dataset[position].id)
        }

        holder.favBtn?.isChecked = dataset[position].isFavorite
        holder.favBtn?.setOnClickListener {
            listener?.favoriteListener(dataset[position], !dataset[position].isFavorite)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataset.size

}
