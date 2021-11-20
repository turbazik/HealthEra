package com.turbazik.healthera.data.api

import com.turbazik.healthera.data.model.LoginDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface AuthAPI {

    @FormUrlEncoded
    @POST("tokens")
    suspend fun login(@Field("username") email: String, @Field("user_password") password: String): Response<LoginDto>
}