package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.model.parentalguidance.PGResponse
import com.pamella.moviesapp.domain.model.ParentalGuidance

class ParentalGuidanceMapper {
    fun map(parentalGuidanceList: List<PGResponse>?): List<ParentalGuidance> {
        val certifications = mutableListOf<ParentalGuidance>()
        parentalGuidanceList?.let {
            parentalGuidanceList.forEach {
                val certification = ParentalGuidance(
                    pg = it.certification,
                    type = it.type
                )
                certifications.add(certification)
            }
        }
        return certifications
    }
}