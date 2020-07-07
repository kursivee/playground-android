package com.kursivee.graphql.login.domain

import com.kursivee.graphql.base.network.NetworkResponse

interface LoginRepository {
    suspend fun login(email: String): NetworkResponse<AuthEntity>
}