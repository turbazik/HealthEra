package com.turbazik.healthera.di

import com.turbazik.healthera.domain.usecase.adherence.PatientsUseCase
import com.turbazik.healthera.domain.usecase.adherence.PatientsUseCaseImpl
import com.turbazik.healthera.domain.usecase.auth.AuthUseCase
import com.turbazik.healthera.domain.usecase.auth.AuthUseCaseImpl

import org.koin.dsl.module

val domainModule = module {
    single<AuthUseCase> {
        AuthUseCaseImpl(
            authRepository = get()
        )
    }
    single<PatientsUseCase> {
        PatientsUseCaseImpl(
            patientsRepository = get()
        )
    }
}
