package com.turbazik.healthera.domain.usecase.auth

interface AuthUseCase {
    suspend fun login(email: String, password: String): LoginEntity
}