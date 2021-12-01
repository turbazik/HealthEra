package com.turbazik.healthera.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.turbazik.healthera.data.mapper.AdherenceEntityMapper
import com.turbazik.healthera.data.repository.AuthRepositoryImpl
import com.turbazik.healthera.data.repository.PatientsRepositoryImpl
import com.turbazik.healthera.domain.repository.AuthRepository
import com.turbazik.healthera.domain.repository.PatientsRepository

import org.koin.dsl.module

val dataModule = module {
    single { provideGson() }
    single<AuthRepository> {
        AuthRepositoryImpl(
            api = get(),
            userStorage = get()
        )
    }
    single<PatientsRepository> {
        PatientsRepositoryImpl(
            api = get(),
            userStorage = get(),
            adherenceEntityMapper = AdherenceEntityMapper()
        )
    }
}

fun provideGson(): Gson = GsonBuilder()
    .setLenient()
    .create()