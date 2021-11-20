package com.turbazik.healthera.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.turbazik.healthera.data.mapper.LoginEntityMapper
import com.turbazik.healthera.data.repository.AuthRepositoryImpl
import com.turbazik.healthera.domain.repository.AuthRepository

import org.koin.dsl.module

val dataModule = module {
    single { provideGson() }
    single<AuthRepository> {
        AuthRepositoryImpl(
            api = get(),
            loginEntityMapper = LoginEntityMapper()
        )
    }
}

fun provideGson(): Gson = GsonBuilder()
    .setLenient()
    .create()