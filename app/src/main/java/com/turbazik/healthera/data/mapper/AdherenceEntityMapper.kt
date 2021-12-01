package com.turbazik.healthera.data.mapper

import com.turbazik.healthera.data.model.AdherenceItemDto
import com.turbazik.healthera.domain.model.AdherenceEntity
import com.turbazik.healthera.utils.Mapper

class AdherenceEntityMapper : Mapper<AdherenceItemDto, AdherenceEntity>() {

    override fun map(from: AdherenceItemDto): AdherenceEntity {
        return AdherenceEntity(
            action = from.action,
            alarmTime = from.alarmTime,
            doseQuantity = from.doseQuantity,
            note = from.note,
            patientId = from.patientId,
            remedyId = from.remedyId
        )
    }
}
