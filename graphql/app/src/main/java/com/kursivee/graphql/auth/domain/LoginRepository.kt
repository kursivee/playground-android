package com.kursivee.graphql.auth.domain

interface LoginRepository {
    suspend fun login(email: String): AuthEntity
}