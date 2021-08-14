package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.model.genres.GenreResponse
import com.pamella.moviesapp.classes.model.Genre

class GenreMapper {
    fun map(genresResponseList: List<GenreResponse>): List<Genre> {
        val genres = mutableListOf<Genre>()
        genresResponseList.forEach {
            val genre = Genre(
                id = it.id,
                name = it.genreName
            )
            genres.add(genre)
        }
        return genres
    }
}