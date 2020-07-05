package com.kursivee.graphql.base.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import okhttp3.OkHttpClient

class ApolloClientFactory(
    private val okHttpClient: OkHttpClient
) {
    companion object {
        const val SERVER_URL = "https://apollo-fullstack-tutorial.herokuapp.com/"
        const val SOCKET_URL = "wss://apollo-fullstack-tutorial.herokuapp.com/graphql"
    }

    fun get(): ApolloClient = ApolloClient.builder()
        .serverUrl(SERVER_URL)
        .subscriptionTransportFactory(
            WebSocketSubscriptionTransport.Factory(SOCKET_URL , okHttpClient)
        )
        .okHttpClient(okHttpClient)
        .build()
}