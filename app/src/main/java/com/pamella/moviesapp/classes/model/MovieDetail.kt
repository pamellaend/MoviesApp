package com.pamella.moviesapp.classes.model

class MovieDetail(
    val backdropPath: String? = null,
    val genres: List<Genre>,
    val id: Int,
    val overview: String? = null,
    val releaseDate: String,
    val runtime: Int? = null,
    val voteAverage: Float,
    val title: String,
    var isFavorite: Boolean = false,
) {


    fun getRuntime(): String {
        runtime?.let {
            return if (runtime < 60) {
                runtime.toString() + "min"
            } else {
                val time = runtime / 60
                val hours = time.toString().substringBefore(".").toInt()
                val minutes = runtime - (hours * 60)
                hours.toString() +
                        "h " +
                        minutes.toString() +
                        "min"
            }
        }
        return ""
    }

    fun getRating(): String {
        val rating = (voteAverage * 10).toInt()
        return "$rating%"
    }

    fun getReleaseYear(): String {
        return releaseDate.take(4)
    }
}

