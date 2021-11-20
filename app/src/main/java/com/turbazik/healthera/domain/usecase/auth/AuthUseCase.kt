package com.turbazik.healthera.domain.usecase.auth

import com.turbazik.healthera.domain.model.LoginEntity

interface AuthUseCase {
    suspend fun login(email: String, password: String): LoginEntity
}