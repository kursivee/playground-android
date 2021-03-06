package com.kursivee.graphql.login.data

import arrow.core.Either
import com.kursivee.graphql.login.domain.AuthEntity
import com.kursivee.graphql.login.domain.LoginRepository
import com.kursivee.graphql.base.network.ErrorCode
import com.kursivee.graphql.base.network.ErrorEntity
import com.kursivee.graphql.base.network.NetworkResponse

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource
): LoginRepository {
    override suspend fun login(email: String): NetworkResponse<AuthEntity> {
        return loginDataSource.login(email).data?.login()?.let { token ->
            Either.right(AuthEntity(token))
        } ?: Either.left(ErrorEntity(ErrorCode.LOGIN_ERROR))
    }
}