package com.kursivee.graphql.booking.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloSubscriptionCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.coroutines.toFlow
import com.heroku.BookTripsMutation
import com.heroku.SubscribeSubscription
import kotlinx.coroutines.flow.Flow

class TripsDataSource(private val client: ApolloClient) {

    private var subscription: ApolloSubscriptionCall<SubscribeSubscription.Data>? = null

    fun subscribeTripCount(): Flow<Response<SubscribeSubscription.Data>> {
        return client.subscribe(SubscribeSubscription()).toFlow()
    }

    fun unsubscribeTripCount() {
        subscription?.cancel()
    }

    suspend fun bookTrip(launchIds: List<String>): Response<BookTripsMutation.Data> {
        return client.mutate(BookTripsMutation(launchIds)).toDeferred().await()
    }
}