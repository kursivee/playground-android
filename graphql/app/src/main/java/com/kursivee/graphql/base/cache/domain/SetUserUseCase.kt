package com.kursivee.graphql.base.cache.domain

class SetUserUseCase(private val sessionRepository: SessionRepository) {
    operator fun invoke(userEntity: UserEntity) {
        sessionRepository.setUserData(userEntity)
    }
}