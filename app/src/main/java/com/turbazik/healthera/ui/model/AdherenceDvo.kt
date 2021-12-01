package com.turbazik.healthera.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AdherenceDvo(
    val action: String?,
    val alarmTime: Int?,
    val doseQuantity: Int?,
    val note: String?,
    val patientId: String?,
    val remedyId: String?,
    val medicineName: String?
) : Parcelable
