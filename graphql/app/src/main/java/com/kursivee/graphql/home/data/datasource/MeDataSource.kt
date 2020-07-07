package com.kursivee.graphql.home.data.datasource

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.heroku.GetMeQuery

class MeDataSource(private val apolloClient: ApolloClient) {
    suspend fun getMeData(): Response<GetMeQuery.Data> {
        return apolloClient.query(GetMeQuery()).toDeferred().await()
    }
}