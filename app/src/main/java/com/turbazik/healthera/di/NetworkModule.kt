package com.turbazik.healthera.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.turbazik.healthera.BuildConfig
import com.turbazik.healthera.data.api.AuthAPI
import com.turbazik.healthera.data.api.PatientsAPI
import com.turbazik.healthera.data.storage.UserStorage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val NETWORK_TIMEOUT_IN_SECONDS = 30L
private const val BASE_URL = "https://api.dev-healthera.co.uk/"
private const val CLIENT_ID_KEY = "client-id"
private const val TOKEN_KEY = "Token"
private const val TOKEN_REQUEST_URL = "token"

val networkModule = module {
    single {
        provideInterceptor(
            userStorage = get()
        )
    }
    single { provideHttpClientBuilder(get()) }
    single { provideHttpClient(get()) }
    single { GsonConverterFactory.create(get()) }
    single { provideRetrofit(get(), get()) }
    single { get<Retrofit>().create(AuthAPI::class.java) }
    single { get<Retrofit>().create(PatientsAPI::class.java) }
}

fun provideInterceptor(userStorage: UserStorage): Interceptor {
    return Interceptor { chain ->
        val builder = chain.request().newBuilder()
        builder.addHeader(CLIENT_ID_KEY, userStorage.clientId)
        if (!chain.request().url.toString().contains(TOKEN_REQUEST_URL))
            builder.addHeader(TOKEN_KEY, userStorage.token)
        chain.proceed(builder.build())
    }
}

fun provideHttpClientBuilder(
    interceptor: Interceptor
): OkHttpClient.Builder {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        builder.addNetworkInterceptor(StethoInterceptor())
    }
    builder.addInterceptor(interceptor)

    return builder
}

fun provideHttpClient(httpClientBuilder: OkHttpClient.Builder): OkHttpClient {
    return httpClientBuilder
        .callTimeout(NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}