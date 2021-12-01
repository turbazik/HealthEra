package com.turbazik.healthera.data.model

import com.google.gson.annotations.SerializedName

data class RemedyDto(
    @SerializedName("data")
    val data: List<RemedyItemDto>?
)

data class RemedyItemDto(
    @SerializedName("medicine_name")
    val medicineName: String?,
)
