package com.kursivee.graphql.booking.data

import com.kursivee.graphql.booking.domain.entity.TripCountEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
@FlowPreview
class TripsInMemDataSource {

    private val channel: ConflatedBroadcastChannel<TripCountEntity> = ConflatedBroadcastChannel()
    private var count = 0

    suspend fun addBookedCount(count: Int = 1) {
        this.count += count
        channel.send(TripCountEntity(this@TripsInMemDataSource.count))
    }

    fun observe(): Flow<TripCountEntity> {
        return channel.asFlow()
    }
}