package com.kursivee.graphql.home.domain

import com.kursivee.graphql.home.domain.entity.TripCountEntity
import com.kursivee.graphql.home.domain.entity.TripsEntity
import kotlinx.coroutines.flow.Flow

interface TripsRepository {
    suspend fun bookTrips(launchIds: List<String>): TripsEntity
    suspend fun subscribeTripCount()
    fun unsubscribeTripCount()
    suspend fun getBookedTripsCount(): Flow<TripCountEntity>
}