package com.kursivee.graphql.base.cache.domain

class ClearSessionUseCase(private val sessionRepository: SessionRepository) {
    operator fun invoke() {
        sessionRepository.clear()
    }
}