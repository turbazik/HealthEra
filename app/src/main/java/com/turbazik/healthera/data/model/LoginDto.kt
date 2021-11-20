package com.turbazik.healthera.data.model

import com.google.gson.annotations.SerializedName


data class LoginDto(
    @SerializedName("aux")
    val aux: AuxDto?,
    @SerializedName("data")
    val data: List<DataDto>?
)

data class AuxDto(
    @SerializedName("tokenPayload")
    val tokenPayload: TokenPayloadDto?
)

data class DataDto(
    @SerializedName("token")
    val token: String?
)

data class TokenPayloadDto(
    @SerializedName("user_id")
    val userId: String?
)