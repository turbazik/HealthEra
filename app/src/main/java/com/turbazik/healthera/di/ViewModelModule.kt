package com.turbazik.healthera.di

import com.turbazik.healthera.ui.adherence.AdherenceViewModel
import com.turbazik.healthera.ui.auth.AuthViewModel
import com.turbazik.healthera.ui.mapper.AdherenceDvoMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthViewModel(
            authUseCase = get()
        )
    }
    viewModel {
        AdherenceViewModel(
            patientsUseCase = get(),
            authUseCase = get(),
            adherenceDvoMapper = AdherenceDvoMapper()
        )
    }
}
