package com.turbazik.healthera.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String)
}