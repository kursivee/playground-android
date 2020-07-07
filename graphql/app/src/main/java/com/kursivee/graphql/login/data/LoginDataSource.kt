package com.kursivee.graphql.login.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.heroku.LoginMutation

class LoginDataSource(private val apolloClient: ApolloClient) {
    suspend fun login(email: String): Response<LoginMutation.Data> {
        return apolloClient.mutate(LoginMutation(Input.fromNullable(email))).toDeferred().await()
    }
}