package com.kursivee.graphql.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCache
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.fetcher.ApolloResponseFetchers.*
import com.example.AllUsersQuery
import com.example.GetUserQuery
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import java.lang.Exception

class MainViewModel : ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()

    private val client: ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl("https://api.graph.cool/simple/v1/ciyz901en4j590185wkmexyex")
            .normalizedCache(memCache)
            .okHttpClient(okHttpClient)
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient()
    }

    private val memCache: LruNormalizedCacheFactory =
        LruNormalizedCacheFactory(EvictionPolicy.builder().maxSizeBytes(10 * 1024).build())

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

    fun logout() {
        client.apolloStore.normalizedCache().clearAll()
        data.value = ""
    }
}