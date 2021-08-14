package com.pamella.moviesapp.activitys.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pamella.moviesapp.data.base.Constants
import com.pamella.moviesapp.R
import com.pamella.moviesapp.classes.model.Cast

class CastRvAdapter(val context: Context, val dataset: MutableList<Cast> = mutableListOf()) :
    RecyclerView.Adapter<CastRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personImg?.let {
            Glide.with(context).load(Constants.BASE_URL_IMAGE.value + dataset[position].profilePath).into(it)
        }
        holder.personName?.text = dataset[position].name
        holder.character?.text = dataset[position].character
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personImg = view.findViewById<ImageView>(R.id.personImg)
        val personName = view.findViewById<TextView>(R.id.personName)
        val character = view.findViewById<TextView>(R.id.personRole)
    }

    override fun getItemCount() = dataset.size
}