package com.kursivee.graphql.home.data

import com.apollographql.apollo.coroutines.toFlow
import com.kursivee.graphql.home.data.mapper.toEntity
import com.kursivee.graphql.home.domain.TripsRepository
import com.kursivee.graphql.home.domain.entity.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import org.koin.ext.scope
import kotlin.coroutines.coroutineContext
import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

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

