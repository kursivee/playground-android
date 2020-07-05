package com.kursivee.graphql.auth.domain

import com.kursivee.graphql.base.cache.domain.SetUserUseCase
import com.kursivee.graphql.base.cache.domain.UserEntity

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val setUserUseCase: SetUserUseCase
) {
    suspend operator fun invoke(email: String) {
        val entity = UserEntity(loginRepository.login(email).token)
        setUserUseCase(entity)
    }
}