package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.model.parentalguidance.ReleaseDatesResponse
import com.pamella.moviesapp.domain.model.ParentalGuidance

class CertificationMapper {
    fun map(certificationList: List<ReleaseDatesResponse>?): List<ParentalGuidance> {
        val certifications = mutableListOf<ParentalGuidance>()
        certificationList?.let {
            certificationList.forEach {
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