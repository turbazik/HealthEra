package com.turbazik.healthera.data.api

import com.turbazik.healthera.data.model.AdherenceDto
import com.turbazik.healthera.data.model.RemedyDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PatientsAPI {

    @GET("patients/{patientId}/adherences")
    suspend fun getAdherences(
        @Path("patientId") patientId: String,
        @Query("start") startTime: Int,
        @Query("end") endTime: Int
    ): Response<AdherenceDto>

    @GET("patients/{patientId}/remedies/{remedyId}")
    suspend fun getRemedy(
        @Path("patientId") patientId: String,
        @Path("remedyId") remedyId: String
    ): Response<RemedyDto>
}
