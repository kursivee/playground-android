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
import com.kursivee.graphql.home.data.TripsDataSource
import com.kursivee.graphql.home.data.TripsInMemDataSource
import com.kursivee.graphql.home.data.TripsRepositoryImpl
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

class MainViewModel : ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()
    val subscription: MutableLiveData<String> = MutableLiveData()

    private val client: ApolloClient by lazy {
        ApolloClient.builder()
                .serverUrl("https://api.graph.cool/simple/v1/ciyz901en4j590185wkmexyex")
                .normalizedCache(memCache)
                .okHttpClient(okHttpClient)
                .build()
    }

    private val herokuClient: ApolloClient by lazy {
        ApolloClient.builder()
                .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/")
                .subscriptionTransportFactory(
                        WebSocketSubscriptionTransport.Factory("wss://apollo-fullstack-tutorial.herokuapp.com/graphql", okHttpClient)
                )
                .okHttpClient(okHttpClient)
                .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", token)
                    .build()
                it.proceed(request)
            }
            .build()
    }


    private val repositoryImpl = TripsRepositoryImpl(TripsDataSource(herokuClient), TripsInMemDataSource())


    private val memCache: LruNormalizedCacheFactory =
        LruNormalizedCacheFactory(EvictionPolicy.builder().maxSizeBytes(10 * 1024).build())

    private var token: String = ""
    private var count = 0

    fun getUsersAndMemCache() {
        viewModelScope.launch {
           try {
               val response = client.query(GetUserQuery("ck72wpgiy119w0166nlpn4lfg")).responseFetcher(CACHE_FIRST).toDeferred().await()
               data.value = response.data!!.User().toString()
            } catch (e: Exception) {
                e.printStackTrace()
                return@launch
            }
        }
    }

    fun loginAndBookTrip() {
        viewModelScope.launch {

            data.value = repositoryImpl.bookTrips(listOf("83")).toString()
        }

    }

    fun subscribe() {
        viewModelScope.launch {
            repositoryImpl.subscribeTripCount()
//            herokuClient.subscribe(SubscribeSubscription()).toFlow()
//                    .collect {
//                        println("YO")
//                        subscription.value = "trips booked = ${count++ + it.data!!.tripsBooked()!!}"
//                    }

        }
        viewModelScope.launch {
            repositoryImpl.getBookedTripsCount().collect {
                subscription.value = "trips booked = ${it.count}"
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            val resp = herokuClient.mutate(LoginMutation(Input.fromNullable("a@g.com"))).toDeferred().await()
            token = resp.data!!.login() ?: ""
        }
    }

    fun logout() {
        client.apolloStore.normalizedCache().clearAll()
        data.value = ""
    }
}