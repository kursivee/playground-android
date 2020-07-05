package com.kursivee.graphql.base.network

import com.kursivee.graphql.base.cache.domain.GetUserUseCase
import okhttp3.Interceptor
import okhttp3.OkHttpClient

class OkHttpClientFactory(
    private val getUserUseCase: GetUserUseCase
) {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    fun get() = OkHttpClient.Builder()
        .addInterceptor(getAuthorizationInterceptor())
        .build()

    private fun getAuthorizationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(AUTHORIZATION_HEADER, getUserUseCase().token ?: "")
                .build()
            println("Token = ${getUserUseCase().token}")
            chain.proceed(request)
        }
    }
}