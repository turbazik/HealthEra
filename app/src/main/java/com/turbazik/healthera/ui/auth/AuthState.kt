package com.turbazik.healthera.ui.auth

sealed class AuthState {
    object Default : AuthState()
    object EmailValidationFailed : AuthState()
    object PasswordValidationFailed : AuthState()
    object StartLoading : AuthState()
    object EndLoading : AuthState()
    class LoginFailed(val message: String) : AuthState()
    class LoginSucceeded(val message: String) : AuthState()
}
