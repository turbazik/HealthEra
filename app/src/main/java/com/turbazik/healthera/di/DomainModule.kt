package com.turbazik.healthera.di

import com.turbazik.healthera.domain.usecase.auth.AuthUseCase
import com.turbazik.healthera.domain.usecase.auth.AuthUseCaseImpl

import org.koin.dsl.module

val domainModule = module {
    single<AuthUseCase> {
        AuthUseCaseImpl(
            authRepository = get()
        )
    }
}
