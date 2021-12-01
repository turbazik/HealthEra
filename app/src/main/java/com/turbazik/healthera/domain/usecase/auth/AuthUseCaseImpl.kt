package com.turbazik.healthera.domain.usecase.auth

import com.turbazik.healthera.domain.repository.AuthRepository

class AuthUseCaseImpl(private val authRepository: AuthRepository) : AuthUseCase {

    override suspend fun login(username: String, password: String) {
        return authRepository.login(
            username = username,
            password = password
        )
    }

    override suspend fun logout() {
        return authRepository.logout()
    }

}
