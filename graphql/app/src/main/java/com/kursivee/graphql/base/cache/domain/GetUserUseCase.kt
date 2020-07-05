package com.kursivee.graphql.base.cache.domain

class GetUserUseCase(
    private val sessionRepository: SessionRepository
) {
    operator fun invoke(): UserEntity {
        return sessionRepository.getUserData()
    }
}