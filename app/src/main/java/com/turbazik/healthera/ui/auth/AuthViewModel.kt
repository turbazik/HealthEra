package com.turbazik.healthera.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbazik.healthera.domain.usecase.auth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<AuthState>(AuthState.Default)

    val viewState: StateFlow<AuthState> = _viewState

    fun onLoginClicked(email: String, password: String) {
        val emailValidationResult = getEmailValidationResult(
            email = email
        )
        val passwordValidationResult = getPasswordValidationResult(
            password = password
        )
        if (!emailValidationResult || !passwordValidationResult) return

        loginRequest(
            email = email,
            password = password
        )
    }

    private fun getEmailValidationResult(email: String): Boolean {
        if (email.isEmpty()) {
            viewModelScope.launch(Dispatchers.Main) {
                _viewState.emit(
                    AuthState.EmailValidationFailed
                )
            }

            return false
        }
        return true
    }

    private fun getPasswordValidationResult(password: String): Boolean {
        if (password.isEmpty()) {
            viewModelScope.launch(Dispatchers.Main) {
                _viewState.emit(
                    AuthState.PasswordValidationFailed
                )
            }

            return false
        }
        return true
    }

    private fun loginRequest(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _viewState.emit(
                    AuthState.StartLoading
                )
            }
            try {
                authUseCase.login(
                    username = email,
                    password = password
                )
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AuthState.LoginSucceeded
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AuthState.LoginFailed(
                            message = e.message.orEmpty()
                        )
                    )
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _viewState.emit(
                        AuthState.EndLoading
                    )
                }
            }
        }
    }
}
