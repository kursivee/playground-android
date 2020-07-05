package com.kursivee.graphql.home.domain

import com.kursivee.graphql.home.domain.entity.TripCountEntity
import kotlinx.coroutines.flow.Flow

class SubscribeTripCountUseCase(private val repository: TripsRepository) {
    suspend operator fun invoke() {
        repository.subscribeTripCount()
    }
}