package com.turbazik.healthera.domain.usecase.auth

interface AuthUseCase {
    suspend fun login(username: String, password: String)
}