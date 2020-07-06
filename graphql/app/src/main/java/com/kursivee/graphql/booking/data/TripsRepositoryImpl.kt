package com.kursivee.graphql.booking.data

import com.kursivee.graphql.booking.data.mapper.toEntity
import com.kursivee.graphql.booking.domain.TripsRepository
import com.kursivee.graphql.booking.domain.entity.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
@FlowPreview
class TripsRepositoryImpl(
    private val dataSource: TripsDataSource,
    private val cache: TripsInMemDataSource
): TripsRepository {

    override suspend fun subscribeTripCount() {
        dataSource.subscribeTripCount().collect {
            cache.addBookedCount()
        }
    }

    override fun unsubscribeTripCount() {
        dataSource.unsubscribeTripCount()
    }

    override suspend fun bookTrips(launchIds: List<String>): TripsEntity {
        return dataSource.bookTrip(launchIds).data?.bookTrips()!!.toEntity()
    }

    override suspend fun getBookedTripsCount(): Flow<TripCountEntity> {
        return cache.observe()
    }
}

