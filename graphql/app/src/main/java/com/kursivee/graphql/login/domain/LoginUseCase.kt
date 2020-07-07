package com.kursivee.graphql.login.domain

import arrow.core.Either
import com.kursivee.graphql.base.cache.domain.SetUserUseCase
import com.kursivee.graphql.base.cache.domain.UserEntity
import com.kursivee.graphql.base.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val setUserUseCase: SetUserUseCase
) {
    suspend operator fun invoke(email: String): NetworkResponse<Unit> {
        return withContext(Dispatchers.IO) {
            loginRepository.login(email).fold(
                ifLeft = { errorEntity ->
                    Either.left(errorEntity)
                },
                ifRight = { authEntity ->
                    setUserUseCase(UserEntity(authEntity.token))
                    Either.right(Unit)
                }
            )
        }
    }
}