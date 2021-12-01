package com.turbazik.healthera.data.repository

import com.turbazik.healthera.data.api.PatientsAPI
import com.turbazik.healthera.data.mapper.AdherenceEntityMapper
import com.turbazik.healthera.data.model.RemedyItemDto
import com.turbazik.healthera.data.storage.UserStorage
import com.turbazik.healthera.domain.model.AdherenceEntity
import com.turbazik.healthera.domain.repository.PatientsRepository

class PatientsRepositoryImpl(
    private val api: PatientsAPI,
    private val adherenceEntityMapper: AdherenceEntityMapper,
    private val userStorage: UserStorage
) : PatientsRepository, NetworkRepository() {

    override suspend fun getAdherences(startTime: Int, endTime: Int): List<AdherenceEntity> {
        val result = ArrayList<AdherenceEntity>()
        val adherenceResponse = request {
            api.getAdherences(
                patientId = userStorage.userId,
                startTime = startTime,
                endTime = endTime
            )
        }
        for (adherence in adherenceResponse.data.orEmpty()) {
            var remedyResponse: RemedyItemDto? = null
            adherence.remedyId?.let {
                remedyResponse = request {
                    api.getRemedy(
                        patientId = userStorage.userId,
                        remedyId = it
                    )
                }.data.orEmpty().firstOrNull()
            }
            result.add(
                adherenceEntityMapper.map(
                    from = adherence
                ).copy(
                    medicineName = remedyResponse?.medicineName
                )
            )
        }
        return result
    }
}