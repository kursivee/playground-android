package com.kursivee.graphql.home.domain.repository

import com.kursivee.graphql.base.network.NetworkResponse
import com.kursivee.graphql.home.domain.entity.BookedTripEntity
import com.kursivee.graphql.home.domain.entity.ProfileEntity

interface MeRepository {
    suspend fun getProfile(): NetworkResponse<ProfileEntity>
    suspend fun getBookedTrips(): NetworkResponse<List<BookedTripEntity>>
}