package com.kursivee.graphql.home.data.repository

import com.kursivee.graphql.base.network.NetworkResponse
import com.kursivee.graphql.home.data.datasource.MeDataSource
import com.kursivee.graphql.home.data.mapping.toBookedTripEntities
import com.kursivee.graphql.home.data.mapping.toProfileEntity
import com.kursivee.graphql.home.domain.entity.BookedTripEntity
import com.kursivee.graphql.home.domain.entity.ProfileEntity
import com.kursivee.graphql.home.domain.repository.MeRepository

class MeRepositoryImpl(private val meDataSource: MeDataSource):
    MeRepository {

    override suspend fun getProfile(): NetworkResponse<ProfileEntity> {
        // TODO: Make this a separate call
        return meDataSource.getMeData().toProfileEntity()
    }

    override suspend fun getBookedTrips(): NetworkResponse<List<BookedTripEntity>> {
        return meDataSource.getMeData().toBookedTripEntities()
    }
}