package com.turbazik.healthera.data.repository

import com.turbazik.healthera.data.api.AuthAPI
import com.turbazik.healthera.data.mapper.LoginEntityMapper
import com.turbazik.healthera.domain.model.LoginEntity
import com.turbazik.healthera.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthAPI,
    private val loginEntityMapper: LoginEntityMapper
) : AuthRepository, NetworkRepository() {

    override suspend fun login(email: String, password: String): LoginEntity {
        val response = request {
            api.login(
                email = email,
                password = password
            )
        }

        return loginEntityMapper.map(from = response)
    }
}