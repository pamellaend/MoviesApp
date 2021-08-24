package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.model.cast.CastResponse
import com.pamella.moviesapp.domain.model.Cast

class CastMapper {
    fun map(castList: List<CastResponse>): List<Cast> {
        val celebrities = mutableListOf<Cast>()
        castList.forEach {
            val celebrity = Cast(
                name = it.name,
                profilePath = it.profilePath,
                character = it.character
            )
            celebrities.add(celebrity)
        }
        return celebrities
    }


}