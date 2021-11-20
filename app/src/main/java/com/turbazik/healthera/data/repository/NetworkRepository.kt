package com.turbazik.healthera.data.repository

import com.google.gson.Gson
import com.turbazik.healthera.data.exceptions.ApiException
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Response

abstract class NetworkRepository {

    private val gson by inject<Gson>(Gson::class.java)

    protected suspend fun <T> request(block: suspend () -> Response<T>): T {
        val response: Response<T> = block()
        if (response.isSuccessful) return response.body()!!

        val errorBody = response.errorBody()?.string()
        if (response.code() >= 500) throw Exception()

        val tempException = gson.fromJson(errorBody, ApiException::class.java)

        throw tempException
    }
}