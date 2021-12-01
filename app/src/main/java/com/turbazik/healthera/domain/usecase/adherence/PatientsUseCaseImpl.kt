package com.turbazik.healthera.domain.usecase.adherence

import com.turbazik.healthera.domain.model.AdherenceEntity
import com.turbazik.healthera.domain.repository.PatientsRepository

class PatientsUseCaseImpl(private val patientsRepository: PatientsRepository) : PatientsUseCase {

    override suspend fun getAdherence(startTime: Int, endTime: Int): List<AdherenceEntity> {
        return patientsRepository.getAdherences(
            startTime = startTime,
            endTime = endTime
        )
    }

}
