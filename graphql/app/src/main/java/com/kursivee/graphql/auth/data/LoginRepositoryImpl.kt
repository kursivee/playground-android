package com.kursivee.graphql.auth.data

import com.kursivee.graphql.auth.domain.AuthEntity
import com.kursivee.graphql.auth.domain.LoginRepository

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource
): LoginRepository {
    override suspend fun login(email: String): AuthEntity {
        val token = loginDataSource.login(email).data?.login()!!
        println(token)
        return AuthEntity(token)
    }
}