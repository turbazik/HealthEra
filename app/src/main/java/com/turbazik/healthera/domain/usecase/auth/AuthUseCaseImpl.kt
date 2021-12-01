package com.turbazik.healthera.domain.usecase.auth

import com.turbazik.healthera.domain.repository.AuthRepository

class AuthUseCaseImpl(private val authRepository: AuthRepository) : AuthUseCase {

    override suspend fun login(email: String, password: String): LoginEntity {
        return authRepository.login(
            email = email,
            password = password
        )
    }

}
