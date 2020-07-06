package com.kursivee.graphql.booking.domain

class BookTripsUseCase(
    private val repository: TripsRepository
) {
    suspend operator fun invoke(launchIds: List<String>) {
        repository.bookTrips(launchIds)
    }
}