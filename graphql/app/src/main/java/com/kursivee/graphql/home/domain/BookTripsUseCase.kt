package com.kursivee.graphql.home.domain

import com.kursivee.graphql.home.domain.TripsRepository

class BookTripsUseCase(
    private val repository: TripsRepository
) {
    suspend operator fun invoke(launchIds: List<String>) {
//        return repository.bookTrip(launchIds)
    }
}