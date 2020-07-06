package com.kursivee.graphql.booking.domain

class SubscribeTripCountUseCase(private val repository: TripsRepository) {
    suspend operator fun invoke() {
        repository.subscribeTripCount()
    }
}