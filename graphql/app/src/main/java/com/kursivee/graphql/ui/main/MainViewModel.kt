package com.kursivee.graphql.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.coroutines.toFlow
import com.apollographql.apollo.fetcher.ApolloResponseFetchers.CACHE_FIRST
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import com.example.GetUserQuery
import com.heroku.BookTripsMutation
import com.heroku.LoginMutation
import com.heroku.SubscribeSubscription
import com.kursivee.graphql.auth.domain.LoginUseCase
import com.kursivee.graphql.base.cache.domain.SetUserUseCase
import com.kursivee.graphql.base.cache.domain.UserEntity
import com.kursivee.graphql.home.data.TripsDataSource
import com.kursivee.graphql.home.data.TripsInMemDataSource
import com.kursivee.graphql.home.data.TripsRepositoryImpl
import com.kursivee.graphql.home.domain.BookTripsUseCase
import com.kursivee.graphql.home.domain.entity.TripCountEntity
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.koin.ext.scope
import kotlin.coroutines.coroutineContext

class MainViewModel(
    private val loginUseCase: LoginUseCase,
    private val bookTripsUseCase: BookTripsUseCase
) : ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()
    val subscription: MutableLiveData<String> = MutableLiveData()

    fun loginAndBookTrip() {
        viewModelScope.launch {
            bookTripsUseCase(listOf("83"))
        }

    }

    fun login() {
        viewModelScope.launch {
            loginUseCase("email@a.com")
        }
    }
}