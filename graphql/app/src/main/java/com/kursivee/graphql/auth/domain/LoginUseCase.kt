package com.kursivee.graphql.auth.domain

import com.kursivee.graphql.base.cache.domain.SetUserUseCase
import com.kursivee.graphql.base.cache.domain.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val setUserUseCase: SetUserUseCase
) {
    suspend operator fun invoke(email: String) {
        withContext(Dispatchers.IO) {
            val entity = UserEntity(loginRepository.login(email).token)
            setUserUseCase(entity)
        }
    }
}