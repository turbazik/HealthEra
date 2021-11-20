package com.turbazik.healthera.domain.repository

import com.turbazik.healthera.domain.model.LoginEntity

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginEntity
}