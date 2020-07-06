package com.kursivee.graphql.booking.domain

import com.kursivee.graphql.booking.domain.entity.TripCountEntity
import kotlinx.coroutines.flow.Flow

class ObserveTripCountUseCase(private val repository: TripsRepository) {
    suspend operator fun invoke(): Flow<TripCountEntity> {
        return repository.getBookedTripsCount()
    }
}