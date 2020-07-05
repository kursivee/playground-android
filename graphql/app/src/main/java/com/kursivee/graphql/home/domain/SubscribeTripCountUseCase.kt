package com.kursivee.graphql.home.domain

class SubscribeTripCountUseCase(private val repository: TripsRepository) {
    suspend operator fun invoke() {
        repository.subscribeTripCount()
    }
}