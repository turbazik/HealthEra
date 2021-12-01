package com.turbazik.healthera.data.model

import com.google.gson.annotations.SerializedName


data class LoginDto(
    @SerializedName("aux")
    val aux: AuxDto?,
    @SerializedName("data")
    val data: List<DataDto>?,
    val error: Error?
)

data class AuxDto(
    @SerializedName("tokenPayload")
    val tokenPayload: TokenPayloadDto?
)

data class DataDto(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: UserDto?
)

data class TokenPayloadDto(
    @SerializedName("user_id")
    val userId: String?
)

data class UserDto(
    val forename: String?,
    val surname: String?,
)