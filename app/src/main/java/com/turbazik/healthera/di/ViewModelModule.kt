package com.turbazik.healthera.di

import com.turbazik.healthera.ui.auth.AuthViewModel
import com.turbazik.healthera.ui.mapper.LoginDvoMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthViewModel(
            authUseCase = get(),
            loginDvoMapper = LoginDvoMapper()
        )
    }
}
