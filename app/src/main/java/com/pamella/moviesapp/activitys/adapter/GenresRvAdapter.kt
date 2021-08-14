package com.pamella.moviesapp.activitys.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.pamella.moviesapp.R
import com.pamella.moviesapp.classes.model.Genre
import com.pamella.moviesapp.activitys.ClickListener

class GenresRvAdapter(
    val context: Context,
    private val listener: ClickListener? = null,
    var dataset: MutableList<Genre> = mutableListOf()
) : RecyclerView.Adapter<GenresRvAdapter.ViewHolder>() {

    private val selectedItems: MutableList<Int> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreChip: Chip? = view.findViewById(R.id.itemGenre)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genreChip?.text = dataset[position].name

        holder.genreChip?.setOnClickListener {
            if (selectedItems.contains(dataset[position].id)) {
                selectedItems.remove(dataset[position].id)
            } else {
                selectedItems.add(dataset[position].id)
            }
            listener?.loadMoviesWithGenre(selectedItems)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_all_genres, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataset.size
}