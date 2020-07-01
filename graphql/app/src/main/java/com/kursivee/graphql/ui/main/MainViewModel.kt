package com.kursivee.graphql.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.example.AllUsersQuery
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val client: ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl("https://api.graph.cool/simple/v1/ciyz901en4j590185wkmexyex")
            .build()
    }

    fun getUsers() {
        viewModelScope.launch {
            val response = try {
                client.query(AllUsersQuery()).toDeferred().await()
            } catch (e: Exception) {
                e.printStackTrace()
                return@launch
            }

            response.data?.allUsers()?.joinToString("\n") {
                "${it.id()}: ${it.name()}"
            }.also {
                println(it)
            }
        }
    }
}