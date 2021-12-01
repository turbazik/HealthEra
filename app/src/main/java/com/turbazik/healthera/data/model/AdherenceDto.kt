package com.turbazik.healthera.data.model

import com.google.gson.annotations.SerializedName

data class AdherenceDto(
    @SerializedName("data")
    val data: List<AdherenceItemDto>?
)

data class AdherenceItemDto(
    @SerializedName("action")
    val action: String?,
    @SerializedName("alarm_time")
    val alarmTime: Int?,
    @SerializedName("dose_quantity")
    val doseQuantity: Int?,
    @SerializedName("note")
    val note: String?,
    @SerializedName("patient_id")
    val patientId: String?,
    @SerializedName("remedy_id")
    val remedyId: String?
)