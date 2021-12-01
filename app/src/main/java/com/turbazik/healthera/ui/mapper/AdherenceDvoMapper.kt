package com.turbazik.healthera.ui.mapper

import com.turbazik.healthera.domain.model.AdherenceEntity
import com.turbazik.healthera.ui.model.AdherenceDvo
import com.turbazik.healthera.utils.Mapper

class AdherenceDvoMapper : Mapper<AdherenceEntity, AdherenceDvo>() {

    override fun map(from: AdherenceEntity): AdherenceDvo {
        return AdherenceDvo(
            action = from.action,
            alarmTime = from.alarmTime,
            doseQuantity = from.doseQuantity,
            note = from.note,
            patientId = from.patientId,
            remedyId = from.remedyId,
            medicineName = from.medicineName
        )
    }
}
