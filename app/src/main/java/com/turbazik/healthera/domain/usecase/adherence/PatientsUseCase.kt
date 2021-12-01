package com.turbazik.healthera.domain.usecase.adherence

import com.turbazik.healthera.domain.model.AdherenceEntity

interface PatientsUseCase {

    suspend fun getAdherence(
        startTime: Int,
        endTime: Int
    ): List<AdherenceEntity>
}
