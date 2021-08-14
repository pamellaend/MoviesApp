package com.pamella.moviesapp.activitys.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.pamella.moviesapp.R
import com.pamella.moviesapp.classes.model.Genre

class MovieDetailsGenresRvAdapter(private var dataset: List<Genre> = mutableListOf()) :
    RecyclerView.Adapter<MovieDetailsGenresRvAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemGenre: Chip? = view.findViewById(R.id.itemGenre)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemGenre?.text = dataset[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_genres, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataset.size
}