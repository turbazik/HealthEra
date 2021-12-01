package com.turbazik.healthera.di

import com.turbazik.healthera.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthViewModel(
            authUseCase = get()
        )
    }
}
