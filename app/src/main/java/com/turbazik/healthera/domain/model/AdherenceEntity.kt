package com.turbazik.healthera.domain.model

data class AdherenceEntity(
    val action: String?,
    val alarmTime: Int?,
    val doseQuantity: Int?,
    val note: String?,
    val patientId: String?,
    val remedyId: String?,
    val medicineName: String? = null
)
