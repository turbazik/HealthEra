package com.turbazik.healthera.domain.repository

import com.turbazik.healthera.domain.model.AdherenceEntity

interface PatientsRepository {
    suspend fun getAdherences(startTime: Int, endTime: Int): List<AdherenceEntity>
}