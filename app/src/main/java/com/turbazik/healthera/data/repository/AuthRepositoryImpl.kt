package com.turbazik.healthera.data.repository

import com.turbazik.healthera.data.api.AuthAPI
import com.turbazik.healthera.data.model.LoginDto
import com.turbazik.healthera.data.storage.UserStorage
import com.turbazik.healthera.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthAPI,
    private val userStorage: UserStorage
) : AuthRepository, NetworkRepository() {

    override suspend fun login(username: String, password: String) {
        val requestBody = HashMap<String, String>()
        requestBody["username"] = username
        requestBody["user_password"] = password
        val response = request {
            api.login(
                body = requestBody
            )
        }
        if (response.error != null) {
            throw Exception(response.error.text)
        }
        saveUserData(response)
    }

    override suspend fun logout() {
        val response = request {
            api.logout()
        }
        if (response.error != null) {
            throw Exception(response.error.text)
        }
        userStorage.clear()
    }

    private fun saveUserData(response: LoginDto) {
        userStorage.token = response.data?.first()?.token.orEmpty()
        userStorage.userId = response.aux?.tokenPayload?.userId.orEmpty()
        userStorage.name = response.data?.first()?.user?.forename.orEmpty()
        userStorage.surname = response.data?.first()?.user?.surname.orEmpty()
    }
}