package com.turbazik.healthera.data.api

import com.turbazik.healthera.data.model.LoginDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body

interface AuthAPI {

    @POST("tokens")
    suspend fun login(
        @Body body: HashMap<String, String>
    ): Response<LoginDto>
}