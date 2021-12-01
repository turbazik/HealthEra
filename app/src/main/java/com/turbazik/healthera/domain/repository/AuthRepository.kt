package com.turbazik.healthera.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String)
}