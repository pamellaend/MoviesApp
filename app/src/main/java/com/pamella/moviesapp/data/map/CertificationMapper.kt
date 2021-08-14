package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.model.certification.ReleaseDatesResponse
import com.pamella.moviesapp.classes.model.Certification

class CertificationMapper {
    fun map(certificationList: List<ReleaseDatesResponse>?): List<Certification> {
        val certifications = mutableListOf<Certification>()
        certificationList?.let {
            certificationList.forEach {
                val certification = Certification(
                    certification = it.certification,
                    type = it.type
                )
                certifications.add(certification)
            }
        }
        return certifications
    }
}